package com.dyp.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *
 * 使用注解是按包全类名排序
 * 使用xml配置是按配置
 */

@WebFilter("*.do")
public class Filter01 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("A");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("A2");
    }

    @Override
    public void destroy() {

    }
}
