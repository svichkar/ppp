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
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.RentJournal;
import com.nixsolutions.model.LoanBean;

@SuppressWarnings("serial")
public class HomePageServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));

		List<RentJournal> rents = factory.getRentJournalDao().getAllRents();
		List<LoanBean> loansBeans = new ArrayList<>();

		for (RentJournal loan : rents) {
			LOG.debug(loan.getRentDate());
			DateTime dt1 = new DateTime(loan.getRentDate());
			DateTime dt2 = new DateTime();	
			int returnBefore = 30;
			if (loan.getReturnDate() == null && Days.daysBetween(dt1, dt2).getDays() > returnBefore) {
				loansBeans.add(new LoanBean(loan));
			}
		}
		request.setAttribute("loans", loansBeans);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/HomePage.jsp");
		rd.forward(request, response);
	}

}
