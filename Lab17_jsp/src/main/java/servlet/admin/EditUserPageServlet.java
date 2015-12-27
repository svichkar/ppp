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
import database.dao.impl.H2DaoFactory;

@WebServlet("/editUserPage.do")
public class EditUserPageServlet extends HttpServlet {

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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("user", userDao.getByUserName(request.getParameter("user")));
		request.setAttribute("role", roleDao.getByRoleId(userDao.getByUserName(request.getParameter("user")).getRoleId()));
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/EditUser.jsp");
		try {
			rs.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
