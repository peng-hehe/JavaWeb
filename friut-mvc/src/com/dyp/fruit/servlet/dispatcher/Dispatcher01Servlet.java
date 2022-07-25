package com.dyp.fruit.servlet.dispatcher;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Dispatcher01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        session.setAttribute("dispatcher","set01");
        System.out.println("开始服务器内部转发dispatcher");
        req.getRequestDispatcher("dispatcher02").forward(req,resp);
    }
}