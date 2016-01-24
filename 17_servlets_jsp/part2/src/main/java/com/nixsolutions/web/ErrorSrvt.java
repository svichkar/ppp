package com.nixsolutions.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ErrorSrvt
 */
public class ErrorSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processError(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}
	
	private void processError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");
		Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String srvtName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		request.setAttribute("ex", ex);
		request.setAttribute("code", code);
		request.setAttribute("servletName", srvtName);
		request.setAttribute("requestUri", requestUri);
		request.setAttribute("title", "Oooups! Error!");
		request.getRequestDispatcher("/WEB-INF/jsp/errorpage.jsp").forward(request, response);
	}



}
