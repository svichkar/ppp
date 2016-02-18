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

import com.nixsolutions.model.BookBean;

@SuppressWarnings("serial")
public class FindBookServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));
		request.getRequestDispatcher("WEB-INF/jsp/FindBook.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		processFindBook(request, response);

		request.getRequestDispatcher("WEB-INF/jsp/FindBook.jsp").forward(request, response);
	}

	private void processFindBook(HttpServletRequest request, HttpServletResponse response) {
		LOG.entry(request.getParameter("button"), request.getParameter("search criteria"),
				request.getParameter("search input"));
		String searchCriteria = request.getParameter("search criteria");
		List<BookBean> allBooks = null;

		switch (searchCriteria) {
		case "all":
			allBooks = BookBean.getAllBookBeans();
			break;
		case "name":
			allBooks = BookBean.getBookBeansByName(request.getParameter("search input"));
			break;
		case "author":
			allBooks = BookBean.getBookBeansByAuthor(request.getParameter("search input"));
			break;
		case "category":
			allBooks = BookBean.getBookBeansByCategory(request.getParameter("search input"));
			request.setAttribute("allBooks", allBooks);
			break;
		default:
			break;
		}

		request.setAttribute("allBooks", allBooks);
	}
}
