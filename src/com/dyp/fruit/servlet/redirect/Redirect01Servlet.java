package com.dyp.fruit.servlet.redirect;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Redirect01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("redirect01被调用");
        System.out.println(req.getSession().getId());
        req.getSession().setAttribute("redirect","set01");
        System.out.println("redirect01开始重定向，response");
        resp.sendRedirect("redirect02");
    }
}
