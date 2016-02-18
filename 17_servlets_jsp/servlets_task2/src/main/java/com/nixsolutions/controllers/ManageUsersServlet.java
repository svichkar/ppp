
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

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));
		List<User> users = factory.getUserDao().getAllUsers();
		List<Role> allRoles = factory.getRoleDao().getAllRoles();

		request.setAttribute("users", users);
		request.setAttribute("roles", allRoles);

		request.getRequestDispatcher("WEB-INF/jsp/ManageUsers.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		processUsers(request, response);
	}

	private void processUsers(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String usr = request.getParameter("username");
		String pswd = request.getParameter("password");
		String roleName = request.getParameter("selectrole");
		String action = request.getParameter("action");
		Role role = factory.getRoleDao().getRoleByName(roleName);
		
		LOG.debug(">>>>>>User name: " + usr + "; pass: " + pswd + "; role: " + roleName + "; action: " + action);

		if (action.equals("edit")) {
			User updUser = factory.getUserDao()
					.getUserById(Integer.parseInt(request.getParameter("userid")));
			updUser.setRoleId(role.getRoleId());
			updUser.setUserName(usr);
			updUser.setUserPassword(pswd);
			factory.getUserDao().updateUser(updUser);
		}
		if (action.equals("delete")) {
			User delUser = factory.getUserDao()
					.getUserById(Integer.parseInt(request.getParameter("userid")));
			factory.getUserDao().deleteUser(delUser);
		}
		if (action.equals("create")) {
			User createUser = new User(usr, pswd, role.getRoleId());
			factory.getUserDao().createUser(createUser);
		}
		response.sendRedirect("manageusers");
	}
}
