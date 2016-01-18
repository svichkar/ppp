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
import com.nixsolutions.entity.Category;
import com.nixsolutions.entity.Client;
import com.nixsolutions.entity.RentJournal;
import com.nixsolutions.model.LoanBean;

@SuppressWarnings("serial")
public class LoansServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(">>> book send to loan from find book: " + request.getParameter("loaned") + "; vallue of the tobeLoaned" + request.getAttribute("toBeloaned"));

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ManageLoans.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(">>>" + request.getParameterValues("loaned"), request.getAttribute("toBeloaned"), request.getParameter("search input"));

		String[] booksIds = request.getParameterValues("loaned");
		String readerName = request.getParameter("search input");
		List<Book> toBeloaned = new ArrayList<>();
		if (booksIds != null){
			
			for (String bookId : booksIds) {
				toBeloaned.add(factory.getBookDao().getBookById(Long.valueOf(bookId)));
			}
			request.getSession().setAttribute("toBeloaned", toBeloaned);
		}
		
		if (readerName != null) {
			Client reader = factory.getClientDao().getClientByName(readerName);
			LOG.debug("Name search was: " + readerName + "; Reader was retrieved: " + reader);
			List<LoanBean> loans = LoanBean.getBookBeansByClientId(reader.getClientId());

			request.setAttribute("reader", reader);
			request.setAttribute("loans", loans);
		}

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ManageLoans.jsp");
		rd.forward(request, response);
	}
}
