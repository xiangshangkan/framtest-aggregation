package com.jinkme.framtest.front.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName XssFilter
 * @Description Xss脚本过滤器
 * @Author zhouhui
 * @Date 2019/6/24 09:52
 * @Version 1.0
 */
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new XssHttpServletRequestWraper((HttpServletRequest) servletRequest),servletResponse);
    }

    @Override
    public void destroy() {

    }
}
