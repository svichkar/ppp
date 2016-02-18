package com.nixsolutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/librarian")
public class LibrarianServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Object usrRole = request.getSession().getAttribute("usrRole");
		PrintWriter out = response.getWriter();
		LOG.entry(usrRole);
		
		if (request.getSession(false) == null || !"regular".equals(usrRole)) {
			out.print("<p style=\"color:red\">you are not authorized to be here</p>");
		} else {
			response.setContentType("text/html");
			StringBuilder usersTable = new StringBuilder();
			usersTable.append("<head><title>Librarian page</title></head>"
					+ "<body>" + "<h1>Welcome to the Librarian page</h1>"
					+ "<p> Hi, " + request.getSession().getAttribute("usrName")
					+ "!</p>" 
					+ "<form action=\"logout\" method=\"post\"><input type=\"submit\" value=\"Logout\" /></form>"
					+ "</body>");
			out.print(usersTable.toString());
		}
		out.close();
	}
}
