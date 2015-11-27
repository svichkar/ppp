package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.CategoryDao;
import dao.UsersDao;
import dao.impl.DBDaoFactory;
import entity.Category;
import entity.Users;

@WebServlet(name = "updateUser", urlPatterns = "/updateuser")
public class UpdateUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();
		UsersDao user;
		String[] checkBoxState = request.getParameterValues("checkboxstate");

		if (checkBoxState == null) {

			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String role = request.getParameter("select");
			String id = request.getParameter("userid");

			user = factory.getUsersDao();
			user.updateUser(new Users(new Integer(id).intValue(), login,
					password, role));

			List<Users> usersList = user.getAllUsers();
			CategoryDao cf = factory.getCategoryDao();
			List<Category> bookCategories = cf.getAllCategories();
			request.setAttribute("books", bookCategories);
			request.setAttribute("users", usersList);

			getServletContext()
					.getRequestDispatcher("/WEB-INF/jsp/usersList.jsp")
					.forward(request, response);

		} else {
			if (checkBoxState[0].toLowerCase().equals("on")) {
				String id = request.getParameter("userid");

				user = factory.getUsersDao();
				user.deleteUserById(id);
				List<Users> usersList = user.getAllUsers();
				CategoryDao cf = factory.getCategoryDao();
				List<Category> bookCategories = cf.getAllCategories();
				request.setAttribute("books", bookCategories);
				request.setAttribute("users", usersList);
				getServletContext()
						.getRequestDispatcher("/WEB-INF/jsp/usersList.jsp")
						.forward(request, response);

			}
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("GET IS OK ");

	}

}
