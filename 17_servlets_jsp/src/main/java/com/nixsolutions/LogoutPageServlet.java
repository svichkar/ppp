package com.nixsolutions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.getSession().invalidate();
		out.println("<html>"
				+ "<head><title>Logout Page</title></head>"
				+ "<body>"
				+ "<p>You are successfully logged out! Please log in back to application to continue work.</p>"
				+ "<p><a href=\"index.html\">Login Page</a></p>"
				+ "</body>"
				+ "</html>");
		out.close();
	}

}
