package customservlet;

import java.io.IOException;

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

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -1714560100510914933L;
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		try {
			User user = userDao.getUserByLogin(login);
			if (password.equals(user.getUserPassword())) {
				Role role = roleDao.getByPK(user.getRoleId());
				if (role.getRoleName().equals("Administrator")) {
					//response.sendRedirect("adminLoginSucess.do");
					request.getRequestDispatcher("adminLoginSucess.do").forward(request, response);
				} else {
					request.getRequestDispatcher("userLoginSucess.do").forward(request, response);
					//response.sendRedirect("userLoginSucess.do");
					//response.addHeader("", arg1);
					//response.sendRedirect("userLoginSucess.do?login=" + login);
				}
			} else {
				response.sendRedirect("");
			}
		} catch (PersistenceException | NullPointerException e) {
			response.sendRedirect("");
		}
	}
}
