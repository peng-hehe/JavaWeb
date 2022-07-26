package com.dyp.fruit.servlet.redirect;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirect02Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("redirect02被调用");
        System.out.println(req.getSession().getId());
        System.out.println(req.getSession().getAttribute("redirect"));
    }
}
