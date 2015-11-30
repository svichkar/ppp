package com.nixsolutions.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error_handler")
public class ErrorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		request.setAttribute("exception", exception);
		request.setAttribute("statusCode", statusCode);
		request.setAttribute("servletName", servletName);
		request.setAttribute("requestUri", requestUri);
		request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	}
}
