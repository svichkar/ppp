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
@WebFilter(filterName = "AuthenticationFilter",
        urlPatterns = {"/home", "/term", "/student", "/subject", "/journal"})
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        this.context.log(session == null ? "session is null" : "session IS NOT null");
        this.context.log(session.getAttribute("isAdmin") == null ? "isAdmin is null" : String.valueOf(session.getAttribute("isAdmin")));
        this.context.log(session.getAttribute("user") == null ? "user is null" : String.valueOf(session.getAttribute("user")));

        if (session == null || session.getAttribute("user") == null || session.getAttribute("isAdmin") == null) {
            this.context.log("Unauthorized access request");
            session.removeAttribute("isAdmin");
            if(session != null) {
                session.invalidate();
            }
            response.sendRedirect("login");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
