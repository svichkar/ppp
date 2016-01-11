package com.nixsolutions.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;

public class AdminAuthFilter implements Filter{
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
	HttpServletRequest req = (HttpServletRequest) request;
	HttpServletResponse res = (HttpServletResponse) response;
	
	LOG.entry(req.getSession().getAttribute("usrRole"));
	PrintWriter out = response.getWriter();
	
		if (req.getSession(false) == null
				|| req.getSession().getAttribute("usrRole") == null
				|| !req.getSession().getAttribute("usrRole")
						.equals("admin")){
							out.print(
									"<p style=\"color:red\">you are not authorized to be here</p>"); 
							RequestDispatcher rd = request.getRequestDispatcher("index.html");
							rd.include(request, response);
						}else {
							chain.doFilter(request,response);
						}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
