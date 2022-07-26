package com.dyp.fruit.controllers;

import org.junit.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Demo01Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

        ServletConfig servletConfig = getServletConfig();
        String key01 = servletConfig.getInitParameter("key01");
        System.out.println(key01);
    }

}
