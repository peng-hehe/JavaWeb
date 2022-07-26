package com.dyp.fruit.servlet;

import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.service.FruitService;
import com.dyp.fruit.servlet.thymeleaf.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    private FruitService fruitService = new FruitService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        resp.sendRedirect("index");


    }
}
