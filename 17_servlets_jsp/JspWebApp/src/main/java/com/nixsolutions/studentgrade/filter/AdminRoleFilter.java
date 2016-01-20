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

    private FilterConfig filterConfig;
    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
        this.context = filterConfig.getServletContext();
        this.context.log("AdminRoleFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        this.context.log(String.valueOf(isAdmin));

        if (session == null || isAdmin == null || isAdmin == false ) {
            this.context.log("Unauthorized access request");
            session.removeAttribute("isAdmin");
            session.removeAttribute("user");
            if(session != null) {
                session.invalidate();
            }
            RequestDispatcher rd = servletRequest.getRequestDispatcher("login");
            servletRequest.setAttribute("error", "<h5>You can't access admin page. Please login</h5>");
            rd.include(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
