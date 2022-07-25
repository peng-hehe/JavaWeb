package com.dyp.fruit.servlet;

import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.service.FruitService;
import com.dyp.fruit.util.StringUtil;
import com.dyp.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {
    private FruitService fruitService = new FruitService();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String operate = req.getParameter("operate");

        if (StringUtil.isEmpty(operate)){
            operate = "index";
        }

        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method  m :methods) {
            String methodName = m.getName();
            if (methodName.equals(operate)){
                try {
                    m.invoke(this,req,resp);
                    return;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
        throw new RuntimeException("没有方法");

    }


    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer pageNo = 1;


        String oper = req.getParameter("oper");
        //如果oper!=null 说明 通过表单的查询按钮点击过来的
        //如果oper是空的，说明 不是通过表单的查询按钮点击过来的

        //分情况获取pageNo和keyword
        String keyword = null ;
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            pageNo = 1;
            keyword = req.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword",keyword);

        }else {
            String pageNoStr = req.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }


            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null){
                keyword = (String) keywordObj;
            }else {
                keyword = "";
            }
        }
        req.getSession().setAttribute("pageNo",pageNo);


        List<Fruit> fruitList = fruitService.getFruitList(pageNo,keyword);
        req.getSession().setAttribute("fruitList", fruitList);


        Integer fruitCount = fruitService.getFruitCount(keyword);
        int pageCount = (fruitCount +5 - 1)/5;
        req.getSession().setAttribute("pageCount",pageCount);



//        req.getRequestDispatcher("index.html").forward(req,resp);
        super.processTemplate("index", req, resp);

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        BigDecimal price = BigDecimal.valueOf(Integer.parseInt(priceStr));
        String fcountStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");
        Fruit fruit = new Fruit(null, fname, price, fcount, remark);
        fruitService.addFruit(fruit);

        resp.sendRedirect("fruit.do");

    }

    private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if (fidStr !=null && fidStr !=""){
            Integer fid = Integer.parseInt(fidStr);
            fruitService.delFruit(fid);
        }

        resp.sendRedirect("fruit.do");
    }



    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fid = req.getParameter("fid");
        Fruit fruit = fruitService.eidtFruit(fid);
        System.out.println(fruit);
        req.setAttribute("com/dyp/fruit",fruit);
        super.processTemplate("edit",req,resp);
    }


    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        BigDecimal price = BigDecimal.valueOf(Integer.parseInt(priceStr));
        String fcountStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");
        String fidStr = req.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        Fruit fruit = new Fruit(fid, fname, price, fcount, remark);

        fruitService.updateFruit(fruit);

//        super.processTemplate("index",req,resp);
        resp.sendRedirect("fruit.do");


    }






}
