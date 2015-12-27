package servlet.admin;

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

@WebServlet("/addNewUser.do")
public class AddNewUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private RoleDao roleDao;

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/AddNewUser.jsp");
		rs.include(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("passwordMessage", "You entered different passwords.");
			RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/AddNewUser.jsp");
			rs.include(request, response);
		} else {
			if (request.getParameter("role").equals("Select role")) {
				request.setAttribute("roleMessage", "You entered different passwords.");
				RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/AddNewUser.jsp");
				rs.include(request, response);
			} else {
				if (userDao.getByUserName(request.getParameter("login")) == null) {
					userDao.create(request.getParameter("login"), request.getParameter("password"),
							request.getParameter("email"), roleDao.getByRoleName(request.getParameter("role")).getId());
					response.sendRedirect("login.do");
				} else {
					request.setAttribute("loginMessage", "This user already exist.");
					RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/AddNewUser.jsp");
					rs.include(request, response);
				}
			}
		}
		out.close();
	}
}
