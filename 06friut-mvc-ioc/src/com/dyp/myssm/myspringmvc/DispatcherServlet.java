package com.dyp.myssm.myspringmvc;

import com.dyp.fruit.util.StringUtil;
import com.dyp.myssm.ioc.BeanFactory;
import com.dyp.myssm.ioc.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    private BeanFactory beanFactory;

    public DispatcherServlet() {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();


    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        //假设url是：  http://localhost:8080/pro15/hello.do
        //那么servletPath是：    /hello.do
        // 我的思路是：
        // 第1步： /hello.do ->   hello   或者  /fruit.do  -> fruit
        // 第2步： hello -> HelloController 或者 fruit -> FruitController
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastIndexOf);

        //获取处理对象
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        //获取处理方法
        String operate = request.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index" ;
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method: methods) {

                //找到要处理的方法
                if (operate.equals(method.getName())){

                    //1。设置参数
                    //获取方法需要的参数数组
                    Parameter[] parameters = method.getParameters();

                    //放参数值
                    Object[] parametersValues = new Object[parameters.length];

                    //循环参数列表，设置参数值
                    for (int i = 0; i < parametersValues.length; i++) {

                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();

                        if ("request".equals(parameterName)){
                            parametersValues[i] = request;
                        }else if ("response".equals(parameterName)){
                            parametersValues[i] = response;
                        }else if ("session".equals(parameterName)){
                            parametersValues[i] = request.getSession();
                        }else {

                            //从参数中获取参数值
                            String reqParameter = request.getParameter(parameterName);
                            Object reqParameterObj = reqParameter;

                            String typeName = parameter.getType().getName();
                            if (reqParameterObj != null){
                                if ("java.lang.Integer".equals(typeName)){
                                    reqParameterObj = Integer.parseInt(reqParameter);
                                }

                                if ("java.math.BigDecimal".equals(typeName)){
                                    reqParameterObj = BigDecimal.valueOf(Integer.parseInt(reqParameter));
                                }

                            }

                            parametersValues[i] = reqParameterObj;
                        }

                    }


                    //2。调用controller方法
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parametersValues);


                    //3。视图处理
                    String methodReturnStr = (String) returnObj;
                    if (methodReturnStr.startsWith("redirect:")){
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    }else {
                        super.processTemplate(methodReturnStr,request,response);    // 比如：  "edit"
                    }




                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }




    }
}
