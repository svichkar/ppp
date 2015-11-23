package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookFactory;
import entity.Book;

@WebServlet(name = "getBooks", urlPatterns = "/getBooks")
public class BookList extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		PagesVisualization pv = new PagesVisualization();
		BookFactory books = new BookFactory();
		List<Book> booksList = books.find(null, null);
		pv.bookList(out, booksList);
	}

}
