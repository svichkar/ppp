package customservlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.PersistenceException;
import entities.User;
import h2.H2DAOFactoryImpl;
import h2.UserDAOImpl;

@WebServlet("/edit.do")
public class EditServlet extends HttpServlet {

	private static final long serialVersionUID = 4398655092298971342L;
	private static UserDAOImpl userDao;

	@Override
	public void init() {
		try {
			H2DAOFactoryImpl daoFactory = new H2DAOFactoryImpl();
			userDao = (UserDAOImpl) daoFactory.getDao(daoFactory.getContext(), User.class);
		} catch (ClassNotFoundException | IOException | PersistenceException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String prevLogin = request.getParameter("prev_login");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		int role = Integer.parseInt(request.getParameter("role"));
		User user = null;
		if (prevLogin == null) {
			user = new User(login, password, role);
			try {
				userDao.createFrom(user);
			} catch (PersistenceException e) {
				e.printStackTrace();
			}
		} else {
			try {
				user = userDao.getUserByLogin(prevLogin);
				user.setUserLogin(login);
				user.setUserPassword(password);
				user.setRoleId(role);
				userDao.update(user);
			} catch (PersistenceException e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("adminLoginSucess.do");
	}
}
