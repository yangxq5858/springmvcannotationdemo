package com.hx;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author yangxinqiang
 * @create 2018-09-28 10:08
 */
public class MyFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("MyFilter...doFilter...");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
