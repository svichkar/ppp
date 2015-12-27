package servlet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DaoFactory;
import database.dao.RoleDao;
import database.dao.UserDao;
import database.entity.User;
import database.dao.impl.H2DaoFactory;

@WebServlet("/edirUser.do")
public class EditUserServlet extends HttpServlet {

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
		userDao.update(new User(userDao.getByUserName(request.getParameter("loginPrevious")).getId(),
				request.getParameter("login"), request.getParameter("password"), request.getParameter("email"),
				roleDao.getByRoleName(request.getParameter("role")).getId()));
		response.sendRedirect("login.do");
	}
}
