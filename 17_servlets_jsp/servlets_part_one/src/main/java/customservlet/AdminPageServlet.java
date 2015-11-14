package customservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.PersistenceException;
import entities.Role;
import entities.User;
import h2.H2DAOFactoryImpl;
import h2.RoleDAOImpl;
import h2.UserDAOImpl;

@WebServlet("/adminLoginSucess.do")
public class AdminPageServlet extends HttpServlet {

	private static final long serialVersionUID = 420259211680435670L;
	private static RoleDAOImpl roleDao;
	private static UserDAOImpl userDao;

	@Override
	public void init() {
		try {
			H2DAOFactoryImpl daoFactory = new H2DAOFactoryImpl();
			roleDao = (RoleDAOImpl) daoFactory.getDao(daoFactory.getContext(), Role.class);
			userDao = (UserDAOImpl) daoFactory.getDao(daoFactory.getContext(), User.class);
		} catch (ClassNotFoundException | IOException | PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			List<User> userList = userDao.getAll();
			PrintWriter out = response.getWriter();
			out.write("<html><head><title>Admin Page</title></head><body bgcolor=\"#F0FFFF\">"
					+ "<div align=\"center\"><h1> Users </h1></div>" + generateTable(userList) + "</body></html>");
			out.close();
		} catch (PersistenceException e) {
			response.sendRedirect("");
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			List<User> userList = userDao.getAll();
			PrintWriter out = response.getWriter();
			out.write("<html><head><title>Admin Page</title></head><body bgcolor=\"#F0FFFF\">"
					+ "<div align=\"center\"><h1> Users </h1></div>" + generateTable(userList) + "</body></html>");
			out.close();
		} catch (PersistenceException e) {
			response.sendRedirect("");
		}
	}

	private String generateTable(List<User> data) throws PersistenceException {
		StringBuilder sb = new StringBuilder();
		sb.append("<table align=\"center\" width=40% border=1 cellpadding=8>");
		// generate headers
		sb.append("<tr height=40>");
		sb.append("<td width=10%><b>User ID</b></td>");
		sb.append("<td width=30%><b>User Login</b></td>");
		sb.append("<td width=30%><b>User Password</b></td>");
		sb.append("<td width=20%><b>User Role</b></td>");
		sb.append("<td width=10%><b>Action</b></td>");
		sb.append("</tr>");
		// generate data structure
		for (User user : data) {
			sb.append("<tr height=40>");
			sb.append("<td>" + user.getId() + "</td>");
			sb.append("<td>" + user.getUserLogin() + "</td>");
			sb.append("<td>" + user.getUserPassword() + "</td>");
			sb.append("<td>" + roleDao.getByPK(user.getRoleId()).getRoleName() + "</td>");
			sb.append("<td>");
			sb.append("<form action=\"preEdit.do\" method=\"POST\">");
			sb.append("<input type=\"hidden\" name=\"login\" value=\""+ user.getUserLogin() +"\">");
			sb.append("<input type=\"submit\" value=\"Edit\">");
			sb.append("</form>");
			sb.append("</td>");
			sb.append("</tr>");			
		}
		sb.append("<tr height=40>");
		sb.append("<td colspan=\"5\">");
		sb.append("<form action=\"preEdit.do\" method=\"GET\">");
		sb.append("<div align=\"center\">");
		sb.append("<input type=\"submit\" value=\"Add new\">");
		sb.append("</div>");
		sb.append("</form>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		return sb.toString();
	}
}
