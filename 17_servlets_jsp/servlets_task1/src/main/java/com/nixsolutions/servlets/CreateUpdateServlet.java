package com.nixsolutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.User;

@SuppressWarnings("serial")
public class CreateUpdateServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String usr = request.getParameter("username");
		String pswd = request.getParameter("password");
		String roleId = request.getParameter("roleid");
		String userId = request.getParameter("userid");
		String buttnName = request.getParameter("button");
		LOG.debug("User name: " + usr + "; pass: " + pswd+ "; role: " + roleId + "; usrId: " + userId + "; button: " + buttnName);
		
		PrintWriter out = response.getWriter(); // Always close the output writer
				
		if(request.getParameter("button").equals("edit user")){
			User updUser = factory.getUserDao().getUserById(Integer.parseInt(request.getParameter("userid")));
			updUser.setRoleId(Integer.parseInt(roleId));
			updUser.setUserName(usr);
			updUser.setUserPassword(pswd);
			factory.getUserDao().updateUser(updUser);
			out.println("<p1>user was updated</p1>");
			RequestDispatcher rd = request.getRequestDispatcher("admin");
			rd.include(request, response);
		}
		
		if(request.getParameter("button").equals("create user")){
			User createUser = new User(usr, pswd, Integer.parseInt(roleId));
			factory.getUserDao().createUser(createUser);
			out.println("user was created");
			RequestDispatcher rd = request.getRequestDispatcher("admin");
			rd.include(request, response);
		}
	}
}
