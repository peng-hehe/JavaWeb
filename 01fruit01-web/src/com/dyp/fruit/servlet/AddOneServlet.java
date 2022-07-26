package com.dyp.fruit.servlet;

import com.dyp.fruit.pojo.Fruit;
import com.dyp.fruit.service.FruitService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class AddOneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String fname = req.getParameter("fname");
        String pri = req.getParameter("price");
        long l = Long.parseLong(pri);
        BigDecimal price = BigDecimal.valueOf(l);
        String fcou = req.getParameter("fcount");
        int fcount = Integer.parseInt(fcou);
        String remark = req.getParameter("remark");
        Fruit fruit = new Fruit(1,fname, price, fcount,remark);
        System.out.println(fruit);
        FruitService fruitServer = new FruitService();
        boolean b = fruitServer.addFruit(fruit);
        if(b){
            System.out.println("成功");
        }

        HttpSession session = req.getSession();
        System.out.println(session);


    }
}
