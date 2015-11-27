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
import dao.BookInstanceDao;
import dao.CategoryDao;
import dao.impl.DBDaoFactory;
import entity.Book;
import entity.Category;

@WebServlet(name = "updateBook", urlPatterns = "/updateBook")
public class UpdateBook extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookName = request.getParameter("bookName");
		String bookAuthor = request.getParameter("bookAuthor");
		String bookPublisher = request.getParameter("bookPublisher");
		String bookCategory = request.getParameter("bookCategory");
		String bookId = request.getParameter("bookId");
		String[] checkBoxState = request.getParameterValues("checkboxstate");
		DBDaoFactory factory = new DBDaoFactory();
		CategoryDao cf = factory.getCategoryDao();

		BookDao bf = factory.getBookDao();
		if (checkBoxState == null) {
			Integer categoryId = cf.getCategoryIdByName(bookCategory);

			bf.updateBook(new Book(new Integer(bookId).intValue(), bookName,
					bookAuthor, bookPublisher, categoryId.intValue()));

		} else {
			if (checkBoxState[0].toLowerCase().equals("on")) {
				BookInstanceDao bi = factory.getBookInstanceDao();

				if (bi.deleteByBookID(bookId)) {
					bf.deleteById(bookId);
				} else {

					request.setAttribute("error",
							"<script>alert(\"This book is currently in use. Wait when reader will return it or delete it from journal manually.\")</script>");
				}

			}

		}
		List<Book> books = bf.getAllBooks();
		List<Category> categoriesList = cf.getAllCategories();
		request.setAttribute("books",
				bf.addCategoryNameToBookObject(books, categoriesList));
		request.setAttribute("categories", categoriesList);

		getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/booksManagement.jsp")
				.forward(request, response);

	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
