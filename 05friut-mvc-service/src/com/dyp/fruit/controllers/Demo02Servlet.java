package com.dyp.fruit.controllers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//@WebServlet(urlPatterns ={"/demo02","demo03"},
//        initParams = {
//                @WebInitParam(name = "name" ,value="jem"),
//                @WebInitParam(name = "age",value = "18")
//        })
public class Demo02Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

        ServletConfig servletConfig = getServletConfig();
        String name = servletConfig.getInitParameter("name");
        String age = servletConfig.getInitParameter("age");

        System.out.println("注解获取init参数" + name + age);
    }

}
