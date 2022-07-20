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

        List<Fruit> fruitList = fruitService.getFruitList();
        fruitList.forEach(System.out::println);
        req.getSession().setAttribute("fruitList",fruitList);

//        req.getRequestDispatcher("index.html").forward(req,resp);
        super.processTemplate("index",req,resp);

    }
}
