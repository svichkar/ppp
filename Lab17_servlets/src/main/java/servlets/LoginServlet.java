package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DaoFactory;
import database.dao.RoleDao;
import database.dao.UserDao;
import database.entities.User;
import database.h2.H2DaoFactory;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String userName = (String) request.getSession().getAttribute("login");
		PrintWriter out = response.getWriter();
		if (userName != null) {
			int roleId = userDao.getByUserName(userName).getRoleId();
			String role = roleDao.getByRoleId(roleId).getRoleName();
			switch (role.toLowerCase()) {
			case "admin":
				out.write(adminPage());
				break;
			case "manager":
				out.write(userPage(role));
				break;
			case "teacher":
				out.write(userPage(role));
				break;
			case "student":
				out.write(userPage(role));
				break;
			}
		} else {
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.include(request, response);
		}
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("login");
		String password = request.getParameter("password");
		if (userDao.checkUser(userName, password)) {
			request.getSession().setAttribute("login", userName);
			int roleId = userDao.getByUserName(userName).getRoleId();
			String role = roleDao.getByRoleId(roleId).getRoleName();
			switch (role.toLowerCase()) {
			case "admin":
				out.write(adminPage());
				break;
			case "manager":
				out.write(userPage(role));
				break;
			case "teacher":
				out.write(userPage(role));
				break;
			case "student":
				out.write(userPage(role));
				break;
			}
		} else {
			out.println("<font color=red>Sorry, username or password is wrong.</font>");
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.include(request, response);
		}
		out.close();
	}

	private String userPage(String role) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta charset=\"UTF-8\">");
		sb.append("<title>");
		sb.append(role);
		sb.append(" Page");
		sb.append("</title>");
		sb.append("</head>");
		sb.append("<body bgcolor=\"#F0E68C\"><div align=\"center\"><h2>Welcome " + role + "</h2></div>");
		sb.append("<form action=\"logout.do\" method=\"get\">");
		sb.append("<input type=\"submit\" value=\"Logout\">");
		sb.append("</form>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}

	private String adminPage() {
		List<User> users = userDao.getAll();
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta charset=\"UTF-8\">");
		sb.append("<title>Admin Page</title>");
		sb.append("</head>");
		sb.append("<body bgcolor=\"#F0E68C\">");
		sb.append("<form action=\"logout.do\" method=\"get\">");
		sb.append("<input type=\"submit\" value=\"Logout\">");
		sb.append("</form>");
		sb.append("<h2>Users:</h2>");
		sb.append("<table width=\"50%\" border=\"1\">");
		sb.append("<tr>");
		sb.append("<th width=10%>User Id</th>");
		sb.append("<th width=30%>User Name</th>");
		sb.append("<th width=20%>User Role</th>");
		sb.append("<th width=10%>Action</th>");
		sb.append("</tr>");
		for (User user : users) {
			sb.append("<tr>");
			sb.append("<td>" + user.getId() + "</td>");
			sb.append("<td>" + user.getUserName() + "</td>");
			sb.append("<td>" + roleDao.getByRoleId(user.getRoleId()).getRoleName() + "</td>");
			sb.append("<td>");
			sb.append("<form action=\"users.do\" method=\"post\">");
			sb.append("<input type=\"hidden\" name=\"user\" value=\""+ user.getUserName() +"\">");
			sb.append("<input type=submit name=\"edit\" value=\"Edit\" style=\"width: 100%; height: 50%;\" >");
			sb.append("</form>");
			sb.append("<form action=\"delete.do\" method=\"post\">");
			sb.append("<input type=\"hidden\" name=\"user\" value=\""+ user.getUserName() +"\">");
			sb.append("<input type=submit name=\"delete\" value=\"Delete\" style=\"width: 100%; height: 50%;\" >");
			sb.append("</form>");
			sb.append("</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		sb.append("<form action=\"users.do\" method=\"get\">");
		sb.append("<input type=submit name=\"create\" value=\"Add new\">");
		sb.append("</form>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
}
