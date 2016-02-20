package com.nixsolutions.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.bean.UserBean;
import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.RoleDAO;
import com.nixsolutions.studentgrade.dao.UserDAO;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;

@WebServlet("/users")
public class UsersPageServlet extends HttpServlet {

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
			List<UserBean> displayUserList = new ArrayList<>();
			List<User> userList = userDao.findAllUsers();
			for (User t : userList) {
				Role userRole = roleDao.findRoleById(t.getRoleId());
				UserBean user = new UserBean(t, userRole);
				displayUserList.add(user);
			}
			request.setAttribute("users", displayUserList);
			request.setAttribute("roles", roleDao.findAllRoles());
			request.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("delete") != null) {
			Long userId = Long.valueOf(request.getParameter("user_id"));
			User user = userDao.findUserById(userId);
			if (roleDao.findRoleById(user.getRoleId()).getRoleName().equals("admin")) {
				response.sendRedirect("users?message=Admin user cannot be deleted.");
			} else {
				userDao.deleteUser(user);
				if (userDao.findUserById(userId) == null) {
					response.sendRedirect("users?message=Deleted user with id " + userId);
				} else
					response.sendRedirect("users?message=User is not deleted.");
			}
		}
		if (request.getParameter("add") != null) {
			Long userId = Long.valueOf(userDao.findAllUsers().size() + 1);
			if (userDao.findUserByLogin(request.getParameter("newLogin")) != null) {
				response.sendRedirect("users?message=Login already exists");
			} else {
				if (userDao.findUserByEmail(request.getParameter("newEmail")) != null) {
					response.sendRedirect("users?message=Email already exists");
				} else {
					User newUser = new User(userId, request.getParameter("newLogin"),
							request.getParameter("newPassword"), request.getParameter("newEmail"),
							Long.valueOf(request.getParameter("newRole")));
					userDao.createUser(newUser);
					response.sendRedirect("users?message=Added user with id " + userId);
				}
			}
		}
	}
}
