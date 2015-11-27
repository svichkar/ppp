package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UsersDao;
import dao.impl.DBDaoFactory;
import entity.Users;

@WebServlet(name = "AuthorizationServlet", urlPatterns = "/j_security_check")
public class AuthorizationCheck extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();
		response.setContentType("text/html");
		String login = request.getParameter("username");
		String password = request.getParameter("password");
		UsersDao users = factory.getUsersDao();
		String pressedButton = request.getParameter("register");

		// Commented for fastest access , should be uncommented before commit

		if (login.equals("") || password.equals("")) {
			request.setAttribute("error", "Login or password field is empty.");
			getServletContext().getRequestDispatcher("/index.jsp")
					.forward(request, response);
			return;
		}

		if (pressedButton != null && pressedButton.equals("Register")) {
			if (users.addNewUser(new Users(login, password, "user"))) {
				request.setAttribute("error",
						"Registration completed. Please login to the system.");
				getServletContext().getRequestDispatcher("/index.jsp")
						.forward(request, response);
			} else {
				request.setAttribute("error",
						"This login is already exist , please choose another one.");
				getServletContext().getRequestDispatcher("/index.jsp")
						.forward(request, response);
			}
			return;
		}
		List<Users> user = null;
		user = users.getUserInfoByLogin(login);
		if (user.size() != 0) {
			for (Users account : user) {
				if (account.getPassword().equals(password)) {

					request.setAttribute("role", account.getRole());
					getServletContext()
							.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp")
							.forward(request, response);
					return;
				} else {
					request.setAttribute("error",
							"Your login/password is not valid.");
					getServletContext().getRequestDispatcher("/index.jsp")
							.forward(request, response);

				}
			}
		} else {
			request.setAttribute("error", "Your login/password is not valid.");
			getServletContext().getRequestDispatcher("/index.jsp")
					.forward(request, response);

		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

}
