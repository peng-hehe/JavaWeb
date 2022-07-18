package com.dyp.fruit.servlet.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class Session02Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取01向session域中添加的数据
        HttpSession session = req.getSession();
        Object uname = session.getAttribute("uname");
        System.out.println(uname);
    }
}
