package com.nixsolutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.impl.RoleDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.UserDAOImpl;
import com.nixsolutions.studentgrade.entity.User;

@WebServlet("/admin")
public class AdminPageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();			
		String login = String.valueOf(request.getSession().getAttribute("login"));
		if (login!=null) {
			UserDAOImpl userDao = DAOFactory.getUser();
			RoleDAOImpl roleDao = DAOFactory.getRole();
			User user = userDao.findUserByLogin(login.toLowerCase());
			String role = roleDao.findRoleById(user.getRoleId()).getRoleName();
			if ("admin".equals(role))
				getAdminPage(user, out);
			else
				out.println("<html>"
						+ "<head><title>Redirect Page</title></head>"
						+ "<body>"
						+ "<p>You are not admin! Please login to application as admin to see this page.</p>"
						+ "<p><a href=\"index.html\">Login Page</a></p>"
						+ "</body>"
						+ "</html>");				
			}
		else {
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();			
		String sessionLogin = String.valueOf(request.getSession().getAttribute("login"));
		if (sessionLogin != null) {
			UserDAOImpl userDao = DAOFactory.getUser();
			RoleDAOImpl roleDao = DAOFactory.getRole();
			User sessionUser = userDao.findUserByLogin(sessionLogin.toLowerCase());
			String sessionRole = roleDao.findRoleById(sessionUser.getRoleId()).getRoleName();
			if (("admin").equals(sessionRole)) {

				if (request.getParameter("delete") != null) {
					int userId = Integer.valueOf(request.getParameter("id"));
					String role = request.getParameter("role").toLowerCase();
					if (!("admin").equals(role))
						userDao.deleteUser(userDao.findUserById(userId));
				}

				if (request.getParameter("create") != null) {
					int userId = userDao.findAllUsers().size() + 1;
					String login = request.getParameter("login");
					String password = request.getParameter("password");
					String email = request.getParameter("email");
					int roleId = roleDao.findRoleByName(request.getParameter("role")).getRoleId();
					userDao.createUser(userId, login, password, email, roleId);
				}

				if (request.getParameter("edit") != null) {
					int userId = Integer.valueOf(request.getParameter("id"));
					String login = request.getParameter("login");
					String password = request.getParameter("password");
					String email = request.getParameter("email");
					String role = request.getParameter("role");
					int roleId;
					if (login.isEmpty())
						login = userDao.findUserById(userId).getLogin();
					if (password.isEmpty())
						password = userDao.findUserById(userId).getPassword();
					if (email.isEmpty())
						email = userDao.findUserById(userId).getEmail();
					if (role.isEmpty())
						roleId = userDao.findUserById(userId).getRoleId();
					else
						roleId = roleDao.findRoleByName(role).getRoleId();
					User userUpdate = new User(userId, login, password, email, roleId);
					userDao.updateUser(userUpdate);
				}
				getAdminPage(sessionUser, out);
			}
			else
				out.println("<html>"
						+ "<head><title>Redirect Page</title></head>"
						+ "<body>"
						+ "<p>You are not admin! Please login to application as admin to see this page.</p>"
						+ "<p><a href=\"index.html\">Login Page</a></p>"
						+ "</body>"
						+ "</html>");				
			}
		else {
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
	
	private void getAdminPage(User user, PrintWriter out) {
		UserDAOImpl userDao = DAOFactory.getUser();
		RoleDAOImpl roleDao = DAOFactory.getRole();
		List<User> listUsers = userDao.findAllUsers();
		out.println("<html>"
				+ "<head><title>Admin Page</title></head>"
				+ "<body>"
				+ "<h2>Users</h2>"
				+ "<table border=\"1\"><tr><th>LOGIN</th><th>PASSWORD</th><th>EMAIL</th><th>ROLE</th><th>ACTION</th></tr>");
		for (User u : listUsers) {
			out.print("<tr><td>" + u.getLogin() + "</td>"
                    + "<td>**********</td>"
                    + "<td>" + u.getEmail() + "</td>"
                    + "<td>" + roleDao.findRoleById(u.getRoleId()).getRoleName() + "</td>"
                    + "<td><form action=\"edit\" method=\"get\">"
                    + "<input type=\"hidden\" name=\"id\" value=\"" + u.getUserId() + "\">"
                    + "<input type=\"hidden\" name=\"role\" value=\"" + roleDao.findRoleById(u.getRoleId()).getRoleName() + "\">"
                    + "<br /><input type=\"submit\" name=\"edit\" value=\"Edit User\"></form>"
                    + "<form action=\"admin\" method=\"post\">"
                    + "<input type=\"hidden\" name=\"id\" value=\"" + u.getUserId() + "\">"
                    + "<input type=\"hidden\" name=\"role\" value=\"" + roleDao.findRoleById(u.getRoleId()).getRoleName() + "\">"
                    + "<input type=\"submit\" name=\"delete\" value=\"Delete User\"></form></td>"
                    + "</tr>");
			}
		out.println("<form action=\"admin\" method=\"post\""
				+ "<tr><td><input type=\"text\" maxlength=\"25\" size=\"40\" name=\"login\"></td>"
                + "<td><input type=\"password\" maxlength=\"25\" size=\"40\" name=\"password\"></td>"
                + "<td><input type=\"text\" maxlength=\"25\" size=\"40\" name=\"email\"></td>"
                + "<td><input type=\"text\" maxlength=\"25\" size=\"40\" name=\"role\"></td>"
                + "<td><p><input type=\"submit\" name=\"create\" value=\"Create User\"></p>"
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
