package com.nixsolutions.hibernate.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kozlovskij on 1/12/2016.
 */
@WebFilter(filterName = "adminFilter", urlPatterns = {"/categoryManagement", "/userManagement"})
public class AdminFilter implements Filter {
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
            if (session.getAttribute("role").equals("admin")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("index.jsp?message=please login as admin");
            }
        } else {
            response.sendRedirect("index.jsp?message=please login as admin");
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
