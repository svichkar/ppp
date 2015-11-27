package servlets;

import java.io.IOException;
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

@WebServlet(name = "search", urlPatterns = "/search")
public class Search extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();
		BookDao bf = factory.getBookDao();
		String rButton = request.getParameter("group1");
		String searchQuery = request.getParameter("search_bar");
		CategoryDao cat = factory.getCategoryDao();
		List<Category> category = cat.getAllCategories();
		List<Book> books;
		switch (rButton) {
			case "All" :

				books = bf.searchByAllFields(searchQuery);
				request.setAttribute("books",
						bf.addCategoryNameToBookObject(books, category));

				break;
			case "ByName" :
				books = bf.searchByBookName(searchQuery);

				request.setAttribute("books",
						bf.addCategoryNameToBookObject(books, category));

				break;
			case "ByAuthor" :
				books = bf.searchByBookAuthor(searchQuery);

				request.setAttribute("books",
						bf.addCategoryNameToBookObject(books, category));

				break;
			case "ByPublisher" :
				books = bf.searchByBookPublisher(searchQuery);

				request.setAttribute("books",
						bf.addCategoryNameToBookObject(books, category));

				break;
			case "ByCategory" :
				books = bf.getBooksListByCategoryName(searchQuery);
				request.setAttribute("books",
						bf.addCategoryNameToBookObject(books, category));
				break;
			default :
				break;
		}

		getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/searchResults.jsp")
				.forward(request, response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
