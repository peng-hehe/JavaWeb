package com.dyp.fruit.servlet;

import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.service.FruitService;
import com.dyp.fruit.servlet.thymeleaf.ViewBaseServlet;
import com.dyp.fruit.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    private  FruitService fruitService = new FruitService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
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
}
