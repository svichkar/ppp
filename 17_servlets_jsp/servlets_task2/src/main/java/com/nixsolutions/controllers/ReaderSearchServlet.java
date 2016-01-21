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
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

@SuppressWarnings("serial")
public class ReaderSearchServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));
		List<User> users = factory.getUserDao().getAllUsers();
		List<Role> allRoles = factory.getRoleDao().getAllRoles();

		request.setAttribute("users", users);
		request.setAttribute("roles", allRoles);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ReadersSearch.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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
		
		readers = factory.getClientDao().getAllClients();
		request.setAttribute("readers", readers);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ReadersSearch.jsp");
		rd.forward(request, response);
	}
}