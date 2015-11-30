package com.nixsolutions.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error_handler")
public class ErrorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head><title>Handling example</title></head><body>");
		if (statusCode != 500) {
			out.write("<h3>Servlet 3 Exception Handling</h3>");
			out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
			out.write("<strong>Requested URI</strong>:" + requestUri);
		} else {
			out.write("<h3>Servlet 3 Exception Handling</h3>");
			out.write("<ul><li>Servlet Name:" + servletName + "</li>");
			out.write("<li>Exception Name:" + exception.getClass().getName() + "</li>");
			out.write("<li>Requested URI:" + requestUri + "</li>");
			out.write("<li>Exception Message:" + exception.getMessage() + "</li>");
			out.write("</ul>");
		}
		out.write("</body></html>");
	}
}
