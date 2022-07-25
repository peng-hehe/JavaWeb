package com.dyp.fruit.servlet.thymeleaf;

import com.dyp.fruit.dao.FruitDao;
import com.dyp.fruit.dao.impl.FruitDaoImpl;
import com.dyp.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//Servlet从3。0开始支持注解的方式注册
@WebServlet("/index01")
public class ThymeleafIndexServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FruitDao fruitDao = new FruitDaoImpl();
        List<Fruit> fruitList = fruitDao.getFruitList();
        req.getSession().setAttribute("fruitList",fruitList);
        fruitList.forEach(System.out::println);
        super.processTemplate("index",req,resp);
    }
}
