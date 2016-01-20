package com.nixsolutions.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Category;

@SuppressWarnings("serial")
public class AddCategoryServlet extends HttpServlet{
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/AddCategory.jsp");
		rd.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getSession().getAttribute("usrRole"));

		String categoryName = request.getParameter("categoryname");
		factory.getCategoryDao().createCategory(new Category(categoryName));
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/AddCategory.jsp");
		rd.forward(request, response);
	}
}
