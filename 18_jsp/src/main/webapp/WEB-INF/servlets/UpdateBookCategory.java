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

@WebServlet(name = "updateCategory", urlPatterns = "/updateCategory")
public class UpdateBookCategory extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();
		String category = request.getParameter("Category");
		String id = request.getParameter("Id");
		CategoryDao cf = factory.getCategoryDao();
		cf.updateCategory(new Category(new Integer(id).intValue(), category));
		UsersDao users = factory.getUsersDao();
		List<Users> usersList = users.getAllUsers();
		List<Category> bookCategories = cf.getAllCategories();
		request.setAttribute("books", bookCategories);
		request.setAttribute("users", usersList);

		getServletContext().getRequestDispatcher("/WEB-INF/jsp/usersList.jsp")
				.forward(request, response);

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
