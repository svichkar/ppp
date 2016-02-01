package com.nixsolutions.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.RoleDAO;
import com.nixsolutions.studentgrade.dao.UserDAO;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		UserDAO userDao = DAOFactory.getUser();
		User user = userDao.findUserByLogin(login.toLowerCase());
		if (user != null && password.equals(user.getPassword())) {
			RoleDAO roleDao = DAOFactory.getRole();
			Role role = roleDao.findRoleById(user.getRole().getRoleId());
			request.getSession().setAttribute("role", role.getRoleName());
			if ("admin".equals(role.getRoleName()))
				response.sendRedirect("users");
			else				
				response.sendRedirect("students");
		} else {
			response.sendRedirect("index.jsp?message=Your login or password is incorrect. Please try again.");
		}
	}
}