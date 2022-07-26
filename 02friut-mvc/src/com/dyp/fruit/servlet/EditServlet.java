package com.dyp.fruit.servlet;

import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.service.FruitService;
import com.dyp.fruit.servlet.thymeleaf.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {
    private FruitService fruitService = new FruitService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fid = req.getParameter("fid");
        Fruit fruit = fruitService.eidtFruit(fid);
        System.out.println(fruit);
        req.setAttribute("com/dyp/fruit",fruit);
        super.processTemplate("edit",req,resp);
    }
}
