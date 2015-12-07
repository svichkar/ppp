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
import database.h2.H2DaoFactory;

@WebServlet("/create.do")
public class CreateUserServlet extends HttpServlet {

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
		String userName = request.getParameter("login");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		int roleId = roleDao.getByRoleName(role).getRoleId();
		if (userDao.getByUserName(userName) == null){
			userDao.create(userName, password, roleId);
			out.println("<font color=green>User created sucsecfull.</font>");
			response.sendRedirect("login.do");
		}else{
			out.println("<font color=red>This user already exist.</font>");
			response.sendRedirect("users.do");
		}
		out.close();
	}
}
