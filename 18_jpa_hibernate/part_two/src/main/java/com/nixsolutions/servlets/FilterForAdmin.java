package com.nixsolutions.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "adminFilter", urlPatterns = {"/clients", "/orders", "/cars", "/workers"})
public class FilterForAdmin implements Filter {
    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        if (session.getAttribute("role") != null) {
            if (session.getAttribute("role").equals("manager")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("index.jsp?cantLogin=Please log in as manager");
            }
        } else {
            response.sendRedirect("index.jsp?cantLogin=Please log in as manager");
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}