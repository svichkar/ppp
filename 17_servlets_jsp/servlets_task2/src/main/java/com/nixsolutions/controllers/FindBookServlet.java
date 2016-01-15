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
import com.nixsolutions.model.BookBean;

@SuppressWarnings("serial")
public class FindBookServlet extends HttpServlet{
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	private List<Book> books;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		LOG.entry(request.getSession().getAttribute("usrRole"));
		//List<Book> books = factory.getBookDao().getAllBooks();
	//	List<Role> allRoles = factory.getRoleDao().getAllRoles();
		
		//request.setAttribute("books", books);
	//	request.setAttribute("roles", allRoles);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/FindBook.jsp");
		rd.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.entry(request.getParameter("button"), request.getParameter("search criteria"), request.getParameter("search input"));

		//all books
			if (request.getParameter("search criteria").equals("all")) {
				List<BookBean> allBooks = BookBean.getAllBookBeans();
				request.setAttribute("allBooks", allBooks);
				
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/FindBook.jsp");
				rd.forward(request, response);
			}

			//by name
			if (request.getParameter("search criteria").equals("name")) {
				List<BookBean> allBooks = BookBean.getBookBeansByName(request.getParameter("search input"));
				request.setAttribute("allBooks", allBooks);
				
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/FindBook.jsp");
				rd.forward(request, response);
			}

			//by author
			if (request.getParameter("search criteria").equals("author")) {
				List<BookBean> allBooks = BookBean.getBookBeansByAuthor(request.getParameter("search input"));
				request.setAttribute("allBooks", allBooks);
				
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/FindBook.jsp");
				rd.forward(request, response);
				}		
			
			//by category
			if (request.getParameter("search criteria").equals("category")) {
				List<BookBean> allBooks = BookBean.getBookBeansByCategory(request.getParameter("search input"));
				request.setAttribute("allBooks", allBooks);
				
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/FindBook.jsp");
				rd.forward(request, response);
			}
			}
		
	}


