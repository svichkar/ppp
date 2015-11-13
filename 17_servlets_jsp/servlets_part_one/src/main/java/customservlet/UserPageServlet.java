package customservlet;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/userLoginSucess.do")
public class UserPageServlet extends HttpServlet {

	private static final long serialVersionUID = 8634543653607060350L;
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
		String login = request.getParameter("login");
		try {
			User user = userDao.getUserByLogin(login);
			PrintWriter out = response.getWriter();
			out.write("<html><head><title>User Page</title></head><body bgcolor=\"#F0FFFF\">"
					+ "<div align=\"center\"><h1> User </h1></div>" + generateTable(user) + "</body></html>");
			out.close();
		} catch (PersistenceException e) {
			response.sendRedirect("");
		}
	}

	private String generateTable(User user) throws PersistenceException {
		StringBuilder sb = new StringBuilder();
		sb.append("<table align=\"center\" width=40% border=1 cellpadding=8>");
		// generate headers
		sb.append("<tr height=40>");
		sb.append("<td width=10%><b>User ID</b></td>");
		sb.append("<td width=30%><b>User Login</b></td>");
		sb.append("<td width=30%><b>User Password</b></td>");
		sb.append("<td width=30%><b>User Role</b></td>");
		sb.append("</tr>");
		// generate data structure
		sb.append("<tr height=40>");
		sb.append("<td>" + user.getId() + "</td>");
		sb.append("<td>" + user.getUserLogin() + "</td>");
		sb.append("<td>" + user.getUserPassword() + "</td>");
		sb.append("<td>" + roleDao.getByPK(user.getRoleId()).getRoleName() + "</td>");
		sb.append("</tr>");
		sb.append("</table>");
		return sb.toString();
	}
}
