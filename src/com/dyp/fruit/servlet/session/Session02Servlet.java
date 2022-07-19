package com.dyp.fruit.servlet.session;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
