package com.carmold.core.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = {"/*"}, filterName = "xssFilter")
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 打开高级过滤功能
        chain.doFilter(new XssAntiSamyHttpServletRequestWrapper((HttpServletRequest) request), response);
        // chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest)
        // request), response);
    }

    @Override
    public void destroy() {

    }
}
