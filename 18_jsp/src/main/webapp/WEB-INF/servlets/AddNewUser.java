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

@WebServlet(name = "addUser", urlPatterns = "/adduser")
public class AddNewUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String role = request.getParameter("select");
		UsersDao users = factory.getUsersDao();

		users.addNewUser(new Users(login, password, role));
		
		request.setAttribute("users", users.getAllUsers());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/usersList.jsp")
				.forward(request, response);

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
