package com.nixsolutions.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

@SuppressWarnings("serial")
public class AuthServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getParameter("username"), request.getParameter("userpass"));
		processAuthorisation(request, response);
	}

	private void processAuthorisation(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String usr = request.getParameter("username");
		String pswd = request.getParameter("userpass");
		PrintWriter out = response.getWriter();
		User user = factory.getUserDao().getUserByNameAndPswd(usr, pswd);
		HttpSession session = request.getSession();

		LOG.debug("value of the auth button " + request.getParameter("button"));
		String button = request.getParameter("button");

		// click on login
		if (button.equals("login")) {
			response.setContentType("text/html");
			if (user != null) {
				if (user.getRole().getRoleId().equals(1)) {
					session.setAttribute("usrRole", "admin");
					session.setAttribute("usrName", usr);
					response.sendRedirect("homepage");
				} else {
					session.setAttribute("usrRole", "regular");
					session.setAttribute("usrName", usr);
					response.sendRedirect("homepage");
				}
			} else {
				out.print(
						"<p style=\"color:red\">you have entered incorrect user name or password</p>");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
		}

		// click on registration
		if (button.equals("register")) {
			if (factory.getUserDao().getUserByNameAndPswd(usr, pswd) == null) {
				Role librarian = factory.getRoleDao().getRoleById(2l);
				User createUser = new User(usr, pswd, librarian);
				factory.getUserDao().createUser(createUser);
				out.print("<p style=\"color:red\">user created, you can login now</p>");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);

			} else {
				out.print(
						"<p style=\"color:red\">Please choose another name, this one is already taken</p>");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
		}
	}
}
