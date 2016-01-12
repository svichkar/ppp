package com.nixsolutions.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;


	@SuppressWarnings("serial")
	public class AdminServlet extends HttpServlet{
		private static final Logger LOG = LogManager.getLogger();
		private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
		
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
			LOG.entry(request.getSession().getAttribute("usrRole"));
			List<User> users = factory.getUserDao().getAllUsers();
			List<Role> allRoles = factory.getRoleDao().getAllRoles();
			
			request.setAttribute("users", users);
			request.setAttribute("roles", allRoles);
			
			RequestDispatcher rd = request.getRequestDispatcher("AdminPage.jsp");
			rd.forward(request, response);
		}
	
}

