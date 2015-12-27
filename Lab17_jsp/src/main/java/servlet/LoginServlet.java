package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DaoFactory;
import database.dao.RoleDao;
import database.dao.UserDao;
import database.dao.impl.H2DaoFactory;

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
			roleDao = daoFactory.getRoleDao();
			userDao = daoFactory.getUserDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = (String) request.getSession().getAttribute("login");
		PrintWriter out = response.getWriter();
		if (userName != null) {
			int roleId = userDao.getByUserName(userName).getRoleId();
			String role = roleDao.getByRoleId(roleId).getRoleName();
			RequestDispatcher rs;
			switch (role.toLowerCase()) {
			case "administrator":
				System.out.println(userDao.getAll().size());
				request.setAttribute("users", userDao.getAll());
				request.setAttribute("roles", roleDao.getAll());
				rs = request.getRequestDispatcher("/WEB-INF/jsp/AdminHome.jsp");
				rs.forward(request, response);
				break;
			case "manager":
				rs = request.getRequestDispatcher("/WEB-INF/jsp/ManagerHome.jsp");
				rs.forward(request, response);
				break;
			case "teacher":
				rs = request.getRequestDispatcher("index.html");
				rs.include(request, response);
				break;
			case "student":
				rs = request.getRequestDispatcher("index.html");
				rs.include(request, response);
				break;
			}
		} else {
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.include(request, response);
		}
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("login");
		String password = request.getParameter("password");
		RequestDispatcher rs;
		if (userDao.checkUser(userName, password)) {
			request.getSession().setAttribute("login", userName);
			int roleId = userDao.getByUserName(userName).getRoleId();
			String role = roleDao.getByRoleId(roleId).getRoleName();
			switch (role.toLowerCase()) {
			case "administrator":
				System.out.println(userDao.getAll().size());
				request.setAttribute("users", userDao.getAll());
				request.setAttribute("roles", roleDao.getAll());
				rs = request.getRequestDispatcher("/WEB-INF/jsp/AdminHome.jsp");
				rs.forward(request, response);
				break;
			case "manager":
				rs = request.getRequestDispatcher("/WEB-INF/jsp/ManagerHome.jsp");
				rs.forward(request, response);
				break;
			case "teacher":
				out.println("<font color=red>Sorry, not yet ready.</font>");
				rs = request.getRequestDispatcher("index.html");
				rs.include(request, response);
				break;
			case "student":
				out.println("<font color=red>Sorry, not yet ready.</font>");
				rs = request.getRequestDispatcher("index.html");
				rs.include(request, response);
				break;
			}
		} else {
			out.println("<font color=red>Sorry, username or password is wrong.</font>");
			rs = request.getRequestDispatcher("index.html");
			rs.include(request, response);
		}
		out.close();
	}
}
