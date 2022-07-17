package com.dyp.fruit.servlet;

import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.server.FruitServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String pri = req.getParameter("price");
        long l = Long.parseLong(pri);
        BigDecimal price = BigDecimal.valueOf(l);
        String fcou = req.getParameter("fcount");
        int fcount = Integer.parseInt(fcou);
        String remark = req.getParameter("remark");
        Fruit fruit = new Fruit(fname, price, fcount,remark);
        System.out.println(fruit);
        FruitServer fruitServer = new FruitServer();
        boolean b = fruitServer.addFruit(fruit);
        if(b){
            System.out.println("成功");
        }


    }
}
