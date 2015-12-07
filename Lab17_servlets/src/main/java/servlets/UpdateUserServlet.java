package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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

@WebServlet("/edit.do")
public class UpdateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private RoleDao roleDao;

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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String userNameOld = request.getParameter("loginPrevious");
		String userName = request.getParameter("login");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		int roleId = roleDao.getByRoleName(role).getRoleId();
		int studentId = userDao.getByUserName(userNameOld).getId();
		User user = new User(studentId, userName, password, roleId);
		userDao.update(user);
		out.println("<font color=green>User updated sucsecfull.</font>");
		response.sendRedirect("login.do");
		out.close();
	}
}
