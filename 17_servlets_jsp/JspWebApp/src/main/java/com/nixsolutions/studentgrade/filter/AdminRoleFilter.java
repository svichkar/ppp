package com.nixsolutions.studentgrade.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by konstantin on 1/19/2016.
 */
@WebFilter(filterName = "AdminRoleFilter", urlPatterns = "/admin")
public class AdminRoleFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AdminRoleFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        String isAdmin = request.getParameter("isAdmin");

        if (session == null && (isAdmin != null && isAdmin.equals("false"))) {
            this.context.log("Unauthorized access request");
            response.sendRedirect("login.jsp");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
