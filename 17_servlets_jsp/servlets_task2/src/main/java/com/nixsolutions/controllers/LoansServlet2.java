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
public class LoansServlet2 extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(">>>" + request.getParameterValues("loaned"), request.getAttribute("toBeloaned"), request.getParameter("search input"));


		RequestDispatcher rd = request.getRequestDispatcher("loans");
		rd.include(request, response);
	}
}
