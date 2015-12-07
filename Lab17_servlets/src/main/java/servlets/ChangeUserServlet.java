package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DaoFactory;
import database.dao.RoleDao;
import database.dao.UserDao;
import database.entities.User;
import database.h2.H2DaoFactory;

@WebServlet("/users.do")
public class ChangeUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RoleDao roleDao;
	private UserDao userDao;
	
	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();

			roleDao = daoFactory.getRoleDao(daoFactory.getConnection());
			userDao = daoFactory.getUserDao(daoFactory.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.write(createUser());
		out.close();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		User user = userDao.getByUserName(request.getParameter("user"));		
		out.write(editUser(user));
		out.close();
	}
	
	private String createUser(){
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta charset=\"UTF-8\">");
		sb.append("<title>Create User Page</title>");
		sb.append("</head>");
		sb.append("<body bgcolor=\"#F0E68C\">");
		sb.append("<form action=\"logout.do\" method=\"get\">");
		sb.append("<input type=\"submit\" value=\"Logout\">");
		sb.append("</form>");
		sb.append("<h2>Create User:</h2>");
		sb.append("<form action=\"create.do\" method=\"post\">");
		sb.append("<b>Login:</b><br>");
		sb.append("<input type=\"text\" name=\"login\" name=\"login\"><br>");
		sb.append("<b>Password:</b><br> <input type=\"text\" name=\"password\"><br>");
		sb.append("<b>Role:</b><br>");
		sb.append("<select name=\"role\">");
		sb.append("<option>Select role</option>");
		sb.append("<option value=\"Admin\">Administrator</option>");
		sb.append("<option value=\"Manager\">Manager</option>");
		sb.append("<option value=\"Teacher\">Teacher</option>");
		sb.append("<option value=\"Student\">Student</option>");
		sb.append("</select><br>");
		sb.append("<p><input type=\"submit\" value=\"Save\"></p>");
		sb.append("</form>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	
	private String editUser(User user){
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta charset=\"UTF-8\">");
		sb.append("<title>Create User Page</title>");
		sb.append("</head>");
		sb.append("<body bgcolor=\"#F0E68C\">");
		sb.append("<form action=\"logout.do\" method=\"get\">");
		sb.append("<input type=\"submit\" value=\"Logout\">");
		sb.append("</form>");
		sb.append("<h2>Create User:</h2>");
		sb.append("<form action=\"edit.do\" method=\"post\">");
		sb.append("<b>Login:</b><br>");
		sb.append("<input type=\"text\" name=\"login\" value=\"" + user.getUserName() + "\"><br>");
		sb.append("<b>Password:</b><br> <input type=\"text\" name=\"password\" value=\"" + user.getPassword() + "\"><br>");
		sb.append("<b>Role:</b><br>");
		sb.append("<select name=\"role\">");
		sb.append("<option>Select role</option>");
		switch(roleDao.getByRoleId(user.getRoleId()).getRoleName().toLowerCase()){
		case "admin":
			sb.append("<option value=\"Admin\" selected>Administrator</option>");
			sb.append("<option value=\"Manager\">Manager</option>");
			sb.append("<option value=\"Teacher\">Teacher</option>");
			sb.append("<option value=\"Student\">Student</option>");
			break;
		case "manager":
			sb.append("<option value=\"Admin\">Administrator</option>");
			sb.append("<option value=\"Manager\" selected>Manager</option>");
			sb.append("<option value=\"Teacher\">Teacher</option>");
			sb.append("<option value=\"Student\">Student</option>");
			break;
		case "teacher":
			sb.append("<option value=\"Admin\">Administrator</option>");
			sb.append("<option value=\"Manager\">Manager</option>");
			sb.append("<option value=\"Teacher\" selected>Teacher</option>");
			sb.append("<option value=\"Student\">Student</option>");
			break;
		case "student":
			sb.append("<option value=\"Admin\">Administrator</option>");
			sb.append("<option value=\"Manager\">Manager</option>");
			sb.append("<option value=\"Teacher\">Teacher</option>");
			sb.append("<option value=\"Student\" selected>Student</option>");
			break;
		}
		sb.append("</select><br>");
		sb.append("<p><input type=\"submit\" value=\"Save\"></p>");
		sb.append("<input type=\"hidden\" name=\"loginPrevious\" value=\"" + user.getUserName() + "\"><br>");
		sb.append("</form>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
}
