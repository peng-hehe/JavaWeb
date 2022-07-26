package com.dyp.fruit.servlet.dispatcher;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("dispatcher内部转发成功,Dispatcher02Servlet被调用");
        System.out.println(req.getSession().getAttribute("dispatcher"));
    }
}
