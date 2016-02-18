package com.nixsolutions.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.User;

@SuppressWarnings("serial")
//@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private static final Integer ADMIN_ROLE_ID = 1;
	private static final Integer UER_ROLE_ID = 2;
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	 @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.getSession().invalidate();
         response.sendRedirect("index.jsp");
     }
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getParameter("username"), request.getParameter("userpass"));
		processAuthorisation(request, response);
	}

	private void processAuthorisation(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String usr = request.getParameter("username");
		String pswd = request.getParameter("userpass");
		User user = factory.getUserDao().getUserByNameAndPswd(usr, pswd);
		HttpSession session = request.getSession();

		LOG.debug("value of the auth button " + request.getParameter("button"));
		String button = request.getParameter("button");

		// click on login
		if (button.equals("login")) {
			if (user != null) {				
				if (user.getRoleId().equals(ADMIN_ROLE_ID)) {
					session.setAttribute("usrRole", "admin");					
				} else {
					session.setAttribute("usrRole", "regular");					
				}
				session.setAttribute("usrName", usr);
				response.sendRedirect("homepage");
			} else {
				request.setAttribute("status", "you have entered incorrect user name or password");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}

		// click on registration
		if (button.equals("register")) {
			if (factory.getUserDao().getUserByNameAndPswd(usr, pswd) == null) {
				User createUser = new User(usr, pswd, UER_ROLE_ID);
				factory.getUserDao().createUser(createUser);
				request.setAttribute("status", "user created, you can login now");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				request.setAttribute("status", "Please choose another name, this one is already taken");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	}
}
