package com.nixsolutions.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
public class ErrorServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));
		
		 // Analyze the servlet exception
        Exception exception = (Exception)request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)request.getAttribute("javax.servlet.error.servlet_name");
        String requestuestUri = (String)request.getAttribute("javax.servlet.error.requestuest_uri");
        // Set response content type
        response.setContentType("text/html");
        // print the output
        PrintWriter out = response.getWriter();
        out.write("<html><head><title>Handling example</title></head><body>");
        if (statusCode != 500){
            out.write("<h3>Servlet 3 Exception Handling</h3>");
            out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
            out.write("<strong>Requested URI</strong>:" + requestuestUri);
        } else {
            out.write("<h3>Servlet 3 Exception Handling</h3>");
            out.write("<ul><li>Servlet Name:" + servletName + "</li>");
            out.write("<li>Exception Name:" + exception.getClass().getName() + "</li>");
            out.write("<li>Requested URI:" + requestuestUri + "</li>");
            out.write("<li>Exception Message:" + exception.getMessage() + "</li>");
            out.write("</ul>");
        }
        out.write("</body></html>");

		
	}
}