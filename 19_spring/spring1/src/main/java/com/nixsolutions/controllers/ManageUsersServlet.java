
package com.nixsolutions.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
public class ManageUsersServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));
		List<User> users = factory.getUserDao().getAllUsers();
		List<Role> allRoles = factory.getRoleDao().getAllRoles();

		request.setAttribute("users", users);
		request.setAttribute("roles", allRoles);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ManageUsers.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		processUsers(request, response);
	}

	private void processUsers(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String usr = request.getParameter("username");
		String pswd = request.getParameter("password");
		String roleName = request.getParameter("selectrole");
		String userId = request.getParameter("userid");
		String buttnName = request.getParameter("button");
		Role role = factory.getRoleDao().getRoleByName(roleName);
		LOG.debug("User name: " + usr + "; pass: " + pswd + "; role: " + roleName + "; usrId: "
				+ userId + "; button: " + buttnName);

		if (request.getParameter("button").equals("edit user")) {
			User updUser = factory.getUserDao()
					.getUserById(Long.parseLong(request.getParameter("userid")));
			updUser.setRole(role);
			updUser.setUserName(usr);
			updUser.setUserPassword(pswd);
			factory.getUserDao().updateUser(updUser);
			response.sendRedirect("manageusers");
		}

		if (request.getParameter("button").equals("delete user")) {
			User delUser = factory.getUserDao()
					.getUserById(Long.parseLong(request.getParameter("userid")));
			factory.getUserDao().deleteUser(delUser);
			response.sendRedirect("manageusers");
		}

		if (request.getParameter("button").equals("create user")) {
			User createUser = new User(usr, pswd, role);
			factory.getUserDao().createUser(createUser);
			response.sendRedirect("manageusers");
		}
	}
}
