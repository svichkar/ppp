package com.nixsolutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
public class CreateUpdateServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter(); 
		if (request.getSession(false) == null
				|| request.getSession().getAttribute("usrRole") == null
				|| !request.getSession().getAttribute("usrRole")
						.equals("admin")) {
			out.print(
					"<p style=\"color:red\">you are not authorized to be here</p>");
		} else {
			String usr = request.getParameter("username");
			String pswd = request.getParameter("password");
			String roleName = request.getParameter("selectrole");
			String userId = request.getParameter("userid");
			String buttnName = request.getParameter("button");
			Role role = factory.getRoleDao().getRoleByName(roleName);
			LOG.debug("User name: " + usr + "; pass: " + pswd + "; role: "
					+ roleName + "; usrId: " + userId + "; button: "
					+ buttnName);

			if (request.getParameter("button").equals("edit user")) {
				User updUser = factory.getUserDao().getUserById(
						Integer.parseInt(request.getParameter("userid")));
				updUser.setRoleId(role.getRoleId());
				updUser.setUserName(usr);
				updUser.setUserPassword(pswd);
				factory.getUserDao().updateUser(updUser);
				response.sendRedirect("admin");
			}

			if (request.getParameter("button").equals("delete user")) {
				User delUser = factory.getUserDao().getUserById(
						Integer.parseInt(request.getParameter("userid")));
				factory.getUserDao().deleteUser(delUser);
				response.sendRedirect("admin");
			}

			if (request.getParameter("button").equals("create user")) {
				User createUser = new User(usr, pswd, role.getRoleId());
				factory.getUserDao().createUser(createUser);
				response.sendRedirect("admin");
				}
			}
		out.close();
	}
}
