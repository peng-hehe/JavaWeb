package com.dyp.fruit.servlet.dispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
