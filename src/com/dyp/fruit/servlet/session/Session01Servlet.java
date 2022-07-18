package com.dyp.fruit.servlet.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class Session01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取当前会话，没则创建新的
        HttpSession session = request.getSession();

        //获取session串id
        String id = session.getId();
        System.out.println("session的id：" + id);

        //判断是否是新的，新创建的还是带过来的
        boolean aNew = session.isNew();
        System.out.println("是否是新的" + aNew);

        //非激活时间，默认1800秒，30分钟。超过会话失效
        int maxInactiveInterval = session.getMaxInactiveInterval();
        System.out.println("非激活时间" + maxInactiveInterval);

        //强制失效
//        session.invalidate();
        //session创建时间
        long creationTime = session.getCreationTime();
        System.out.println(creationTime);
        //上一次访问时间
        long lastAccessedTime = session.getLastAccessedTime();
        System.out.println(lastAccessedTime);

        session.setAttribute("uname","juan");


    }
}
