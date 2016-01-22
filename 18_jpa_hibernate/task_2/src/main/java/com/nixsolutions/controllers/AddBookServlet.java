package com.nixsolutions.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Author;
import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Category;
import com.nixsolutions.entity.Cell;

@SuppressWarnings("serial")
public class AddBookServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));
		List<Cell> cells = factory.getCellDao().getAllCells();
		List<Category> categories = factory.getCategoryDao().getAllCategories();

		request.setAttribute("cells", cells);
		request.setAttribute("categories", categories);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/AddBook.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		processAddBook(request, response);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/AddBook.jsp");
		rd.forward(request, response);
	}

	private void processAddBook(HttpServletRequest request, HttpServletResponse response) {
		LOG.entry(request.getParameter("bookname"), request.getParameter("authorfirstname"),
				request.getParameter("authorlastname"), request.getParameter("selectcell"),
				request.getParameter("selectcategory"));

		String bookName = request.getParameter("bookname");
		String authorFirstName = request.getParameter("authorfirstname");
		String authorLastName = request.getParameter("authorlastname");
		String cell = request.getParameter("selectcell");
		String category = request.getParameter("selectcategory");
		String count = request.getParameter("count");

		// new book
		Book book = new Book();
		book.setName(bookName);
		book.setCell(factory.getCellDao().getCellByName(cell));
		book.setCategory(factory.getCategoryDao().getCategoryByName(category));
		book.setCount(Integer.valueOf(count));
		book = factory.getBookDao().createBook(book);

		// new or existing author
		Author auth = factory.getAuthorDao().getAuthorByName(authorLastName);
		List<Author> authors = new ArrayList<>();
		if (auth != null) {
			LOG.debug("we have such an author");
			authors.add(auth);
			book.setAuthors(authors);
		} else {
			Author author = new Author();
			auth.setFirstName(authorFirstName);
			auth.setSecondName(authorLastName);
			auth = factory.getAuthorDao().createAuthor(auth);
			LOG.debug("during creation of new book the author was created: " + auth);
			authors.add(auth);
			book.setAuthors(authors);
		}
	}
}
