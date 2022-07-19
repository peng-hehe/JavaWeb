package com.dyp.fruit.servlet.redirect;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
