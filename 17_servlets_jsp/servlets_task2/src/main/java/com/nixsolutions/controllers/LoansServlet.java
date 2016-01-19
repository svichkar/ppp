package com.nixsolutions.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.nixsolutions.entity.RentJournal;
import com.nixsolutions.model.LoanBean;

@SuppressWarnings("serial")
public class LoansServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(">>> book send to loan from find book: " + request.getParameter("loaned")
				+ "; vallue of the tobeLoaned" + request.getAttribute("toBeloaned"));

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ManageLoans.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(">>>" + request.getParameterValues("loaned"), request.getAttribute("toBeloaned"),
				request.getParameter("search input"));

		String[] booksIds = request.getParameterValues("loaned");
		String readerName = request.getParameter("search input");
		String readerId = request.getParameter("current client");
		String[] selectedBooks = request.getParameterValues("selectbook");

		Client reader = null;

		// loaned book list section
		List<Book> toBeloaned = new ArrayList<>();
		if (booksIds != null) {

			for (String bookId : booksIds) {
				toBeloaned.add(factory.getBookDao().getBookById(Long.valueOf(bookId)));
			}
			request.setAttribute("toBeloaned", toBeloaned);
		}

		// reader active loans section (need to be rendered by submited name or
		// current client on page)

		if (readerName != null) {

			reader = factory.getClientDao().getClientByName(readerName);
			if (reader != null) {
				LOG.debug(">>>>>>>>>>>>>>>>Name search was: " + readerName
						+ "; Reader was retrieved: " + reader);
				List<LoanBean> loans = LoanBean.getBookBeansByClientId(reader.getClientId());

				request.setAttribute("reader", reader);
				request.setAttribute("loans", loans);
			} else {
				reader = factory.getClientDao().getClientById(Long.valueOf(readerId));
				List<LoanBean> loans = LoanBean.getBookBeansByClientId(reader.getClientId());

				request.setAttribute("reader", reader);
				request.setAttribute("loans", loans);
			}
		}

		// submit a book to reader
		LOG.debug(">>>>>>>>>>>>>books to be added to loan " + selectedBooks);
		if (selectedBooks != null) {
			for (String id : selectedBooks) {
				RentJournal rent = new RentJournal();
				rent.setBookId(Long.valueOf(id));
				rent.setClientId(reader.getClientId());
				rent.setRentDate(new Date());
				factory.getRentJournalDao().createRent(rent);
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ManageLoans.jsp");
		rd.forward(request, response);
	}
}
