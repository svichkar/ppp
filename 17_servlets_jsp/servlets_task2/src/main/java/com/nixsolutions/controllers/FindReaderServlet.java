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
import com.nixsolutions.entity.Book;
import com.nixsolutions.entity.Client;

@SuppressWarnings("serial")
public class FindReaderServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/FindReader.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		processFindReaders(request, response);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/FindReader.jsp");
		rd.forward(request, response);
	}

	private void processFindReaders(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("entered the method");
		String[] booksIds = request.getParameterValues("loaned");
		List<Client> readers = new ArrayList<>();

		// loaned book list section
		List<Book> toBeloaned = new ArrayList<>();
		if (booksIds != null) {
			for (String bookId : booksIds) {
				toBeloaned.add(factory.getBookDao().getBookById(Long.valueOf(bookId)));
			}
			request.setAttribute("toBeloaned", toBeloaned);
		}

		if ("search".equals(request.getParameter("button"))) {
			readers = factory.getClientDao().getClientsByName(request.getParameter("search input"));
			request.setAttribute("readers", readers);
		}
	}
}