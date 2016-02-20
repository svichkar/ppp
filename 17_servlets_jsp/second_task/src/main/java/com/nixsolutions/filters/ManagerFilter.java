package com.nixsolutions.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "managerFilter", urlPatterns = { "/students", "/journal", "/journals", "/subjects", "/terms",
		"/updateJournal", "/updateStudent", "/updateSubject", "/updateTerm" })
public class ManagerFilter implements Filter {
	private FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String role = String.valueOf(req.getSession().getAttribute("role"));
		if ("manager".equals(role)) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect("index.jsp?message=Your are not a manager. Please login as manager to continue work.");
		}

	}

	@Override
	public void destroy() {
		this.filterConfig = null;

	}

}
