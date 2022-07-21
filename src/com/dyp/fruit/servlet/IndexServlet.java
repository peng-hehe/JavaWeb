package com.dyp.fruit.servlet;

import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.service.FruitService;
import com.dyp.fruit.servlet.thymeleaf.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    private  FruitService fruitService = new FruitService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer pageNo = 1;
        String pageNoStr = req.getParameter("pageNo");
        if (pageNoStr != null && pageNoStr != "") {
            pageNo = Integer.parseInt(pageNoStr);
        }
        req.getSession().setAttribute("pageNo",pageNo);

        List<Fruit> fruitList = fruitService.getFruitList(pageNo);
        req.getSession().setAttribute("fruitList", fruitList);

        Integer fruitCount = fruitService.getFruitCount();

        int pageCount = (fruitCount +5 - 1)/5;
        req.getSession().setAttribute("pageCount",pageCount);



//        req.getRequestDispatcher("index.html").forward(req,resp);
        super.processTemplate("index", req, resp);

    }
}
