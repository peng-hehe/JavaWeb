package com.dyp.fruit.servlet;

import com.dyp.fruit.service.FruitService;
import com.dyp.fruit.servlet.thymeleaf.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    private FruitService fruitService = new FruitService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if (fidStr !=null && fidStr !=""){
            Integer fid = Integer.parseInt(fidStr);
            fruitService.delFruit(fid);
        }

        resp.sendRedirect("index");
    }
}
