package com.nixsolutions.controllers;

import java.io.IOException;
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

@SuppressWarnings("serial")
public class FindBookServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private static final H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/FindBook.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		processFindBook(request, response);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/FindBook.jsp");
		rd.forward(request, response);
	}

	private void processFindBook(HttpServletRequest request, HttpServletResponse response) {
		LOG.entry(request.getParameter("button"), request.getParameter("search criteria"),
				request.getParameter("search input"));
		String searchCriteria = request.getParameter("search criteria");
		List<Book> allBooks = null;

		switch (searchCriteria) {
		case "all":
			allBooks = factory.getBookDao().getAllBooks();//BookBean.getAllBookBeans();
			break;
		case "name":
			allBooks = factory.getBookDao().getBooksByName(request.getParameter("search input"));//BookBean.getBookBeansByName(request.getParameter("search input"));
			break;
		case "author":
			allBooks = factory.getBookDao().getBooksByAuthor(request.getParameter("search input"));//BookBean.getBookBeansByAuthor(request.getParameter("search input"));
			break;
		case "category":
			allBooks = factory.getBookDao().getBooksByCategory(request.getParameter("search input"));//BookBean.getBookBeansByCategory(request.getParameter("search input"));
			request.setAttribute("allBooks", allBooks);
			break;
		default:
			break;
		}

		request.setAttribute("allBooks", allBooks);
	}
}
