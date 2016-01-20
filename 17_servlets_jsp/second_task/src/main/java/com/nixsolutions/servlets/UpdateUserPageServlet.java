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
import com.nixsolutions.studentgrade.entity.User;

@WebServlet("/updateUser")
public class UpdateUserPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static UserDAO userDao;
	private static RoleDAO roleDao;

	@Override
	public void init() throws ServletException {
		userDao = DAOFactory.getUser();
		roleDao = DAOFactory.getRole();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = String.valueOf(request.getSession().getAttribute("role"));
		if ("admin".equals(role)) {
			if (request.getParameter("user_id") != null) {
				Long userId = Long.valueOf(request.getParameter("user_id"));
				User updateUser = userDao.findUserById(userId);
				request.setAttribute("updateUser", updateUser);
				request.setAttribute("roles", roleDao.findAllRoles());
				request.getRequestDispatcher("/WEB-INF/jsp/updateUser.jsp").forward(request, response);
			} else
				response.sendRedirect("users?message=Please select user for update");
		} else {
			response.sendRedirect("index.jsp?message=Your are not an admin. Please login as admin to continue work.");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			Long userId = Long.valueOf(request.getParameter("user_id"));
			User updatedUser = new User(userId, request.getParameter("updatedLogin"),
					request.getParameter("updatedPassword"), request.getParameter("updatedEmail"),
					Long.valueOf(request.getParameter("updatedRole")));
			userDao.updateUser(updatedUser);
			response.sendRedirect("users?message=Updated user with id " + userId);
		}
	}
}
