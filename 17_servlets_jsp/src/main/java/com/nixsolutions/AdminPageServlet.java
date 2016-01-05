package com.nixsolutions;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();	
		UserDAOImpl userDao = DAOFactory.getUser();
		RoleDAOImpl roleDao = DAOFactory.getRole();
		
		if (request.getParameter("delete") != null) {
            int userId = Integer.valueOf(request.getParameter("id"));
            userDao.deleteUser(userDao.findUserById(userId));
        }
		
		if (request.getParameter("create") != null) {
            int userId = Integer.valueOf(request.getParameter("id"));
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            int roleId = roleDao.findRoleByName(request.getParameter("role")).getRoleId();
            userDao.createUser(userId, login, password, roleId);
        }
		
		if (request.getParameter("edit") != null) {
            int userId = Integer.valueOf(request.getParameter("id"));
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            int roleId = roleDao.findRoleByName(request.getParameter("role")).getRoleId();
            User userUpdate = new User (userId, login, password, roleId);
            userDao.updateUser(userUpdate);
        }
		
		List<User> listUsers = userDao.findAllUsers();
		out.println("<html>"
				+ "<head><title>Admin Page</title></head>"
				+ "<body>"
				+ "<h2>Users</h2>"
				+ "<table border=\"1\"><tr><th>USER_ID</th><th>LOGIN</th><th>PASSWORD</th><th>ROLE</th><th>ACTION</th></tr>");
		for (User u : listUsers) {
			out.print("<tr><td>" + u.getUserId() + "</td>"
                    + "<td>" + u.getLogin() + "</td>"
                    + "<td>" + u.getPassword() + "</td>"
                    + "<td>" + roleDao.findRoleById(u.getRoleId()).getRoleName() + "</td>"
                    + "<td><form action=\"admin\" method=\"post\">"
                    + "<input type=\"hidden\" name=\"id\" value=\"" + u.getUserId() + "\">"                   
                    + "<br /><input type=\"submit\" name=\"delete\" value=\"Delete User\"></form></td>"
                    + "</tr>");
			}
		out.println("<form action=\"admin\" method=\"post\""
				+ "<tr><td><input type=\"text\" maxlength=\"25\" size=\"10\" name=\"id\"></td>"
                + "<td><input type=\"text\" maxlength=\"25\" size=\"40\" name=\"login\"></td>"
                + "<td><input type=\"text\" maxlength=\"25\" size=\"40\" name=\"password\"></td>"
                + "<td><input type=\"text\" maxlength=\"25\" size=\"40\" name=\"role\"></td>"
                + "<td><p><input type=\"submit\" name=\"create\" value=\"Create User\">   "
                + "<input type=\"submit\" name=\"edit\" value=\"Edit User\"></p>"
                + "</td></tr></form>"
				+ "</table>"
				+ "<br /><form action=\"main\" method=\"get\">"
				+ "<input type=\"submit\" name=\"Go to Main Page\" value=\"Go to Main Page\">"
				+ "</form>"
				+ "<form action=\"logout\" method=\"post\">"
				+ "<input type=\"submit\" name=\"Log Out\" value=\"Log Out\">"
				+ "</form>"
				+ "</body>"
				+ "</html>");
		out.close();		
	}

}
