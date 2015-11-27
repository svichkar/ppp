package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.CategoryDao;

import dao.impl.DBDaoFactory;
import entity.Book;
import entity.Category;

@WebServlet(name = "addBook", urlPatterns = "/addBook")
public class AddNewBook extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();
		String bookName = request.getParameter("bookName");
		String bookAuthor = request.getParameter("bookAuthor");
		String bookPublisher = request.getParameter("bookPublisher");
		String categoryValue = request.getParameter("select");
		CategoryDao cat = factory.getCategoryDao();
		
		Integer categoryId = cat.getCategoryIdByName(categoryValue);
						

		BookDao bf = factory.getBookDao();

		bf.addNewBook(new Book(0,bookName, bookAuthor, bookPublisher, categoryId));

		List<Book> books = bf.getAllBooks();

		List<Category> categoriesList = cat.getAllCategories();

		request.setAttribute("books",
				bf.addCategoryNameToBookObject(books, categoriesList));
		request.setAttribute("categories", categoriesList);

		getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/booksManagement.jsp")
				.forward(request, response);

	}

}
