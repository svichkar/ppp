package com.nixsolutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.impl.RoleDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.UserDAOImpl;
import com.nixsolutions.studentgrade.entity.User;

@WebServlet("/edit")
public class EditPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String login = String.valueOf(request.getSession().getAttribute("login"));
		if (login != null) {
			UserDAOImpl userDao = DAOFactory.getUser();
			RoleDAOImpl roleDao = DAOFactory.getRole();
			User user = userDao.findUserByLogin(login.toLowerCase());
			String role = roleDao.findRoleById(user.getRoleId()).getRoleName();
			if ("admin".equals(role)) {
				if (request.getParameter("id") != null) {
					int userId = Integer.valueOf(request.getParameter("id"));
					if (user.getUserId() != userId) {
						User userUpdate = userDao.findUserById(userId);
						getEditPage(userUpdate, out);
					} else
						out.println("<html>"
								+ "<head><title>Redirect Page</title></head>"
								+ "<body>"
								+ "<p>Current user cannot edit itself. Please select another user for update</p>"
								+ "<p><a href=\"admin\">Admin Page</a></p>"
								+ "</body>"
								+ "</html>");
				} else
					out.println("<html>"
							+ "<head><title>Redirect Page</title></head>"
							+ "<body>"
							+ "<p>Please select user for update</p>"
							+ "<p><a href=\"admin\">Admin Page</a></p>"
							+ "</body>"
							+ "</html>");
			} else
				out.println("<html>"
						+ "<head><title>Redirect Page</title></head>"
						+ "<body>"
						+ "<p>You are not admin! Please login to application as admin to see this page.</p>"
						+ "<p><a href=\"index.html\">Login Page</a></p>"
						+ "</body>"
						+ "</html>");
		} else {
			out.println("<html>"
					+ "<head><title>Redirect Page</title></head>"
					+ "<body>"
					+ "<p>Please login to application.</p>"
					+ "<p><a href=\"index.html\">Login Page</a></p>"
					+ "</body>"
					+ "</html>");
		}
		out.close();
	}

	private void getEditPage(User user, PrintWriter out) {
		RoleDAOImpl roleDao = DAOFactory.getRole();
		out.println("<html>"
				+ "<head><title>Admin Page</title></head>"
				+ "<body>"
				+ "<h2>User</h2>"
				+ "<table border=\"1\"><th>LOGIN</th><th>PASSWORD</th><th>EMAIL</th><th>ROLE</th><th>ACTION</th></tr>"
				+ "<form action=\"admin\" method=\"post\"" + "<tr><input type=\"hidden\" name=\"id\" value=\""
				+ user.getUserId() + "\">"
				+ "<td><input type=\"text\" maxlength=\"25\" size=\"40\" name=\"login\" value=\""
				+ user.getLogin() + "\"></td>"
				+ "<td><input type=\"text\" maxlength=\"25\" size=\"40\" name=\"password\" value=\""
				+ user.getPassword() + "\"></td>"
				+ "<td><input type=\"text\" maxlength=\"25\" size=\"40\" name=\"email\" value=\""
				+ user.getEmail() + "\"></td>"
				+ "<td><input type=\"text\" maxlength=\"25\" size=\"40\" name=\"role\" value=\""
				+ roleDao.findRoleById(user.getRoleId()).getRoleName() + "\"></td>"
				+ "<td><p><input type=\"submit\" name=\"edit\" value=\"Edit User\"></p>"
				+ "</td></tr></form>"
				+ "</table>"
				+ "<br /><form action=\"main\" method=\"get\">"
				+ "<input type=\"submit\" name=\"Go to Main Page\" value=\"Go to Main Page\">"
				+ "</form>"
				+ "<form action=\"logout\" method=\"get\">"
				+ "<input type=\"submit\" name=\"Log Out\" value=\"Log Out\">"
				+ "</form>"
				+ "</body>"
				+ "</html>");
	}

}
