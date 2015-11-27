package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BookDao;
import dao.CategoryDao;
import dao.impl.DBDaoFactory;

@WebServlet(name = "getBooks", urlPatterns = "/getBooks")
public class BookList extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();
		BookDao bf = factory.getBookDao();
		CategoryDao cf = factory.getCategoryDao();
		request.setAttribute("books", bf.addCategoryNameToBookObject(
				bf.getAllBooks(), cf.getAllCategories()));
		request.setAttribute("categories", cf.getAllCategories());

		getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/booksManagement.jsp")
				.forward(request, response);

	}

}
