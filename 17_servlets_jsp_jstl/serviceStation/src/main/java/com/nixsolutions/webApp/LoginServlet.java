package com.nixsolutions.webApp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.app.CreateDB;
import com.nixsolutions.app.DropDB;
import com.nixsolutions.app.FillingDB;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.impl.UserDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
// @WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger();

	@Override
	public void init() throws ServletException {
		try {
			DropDB.dropDB();
			CreateDB.createDB();
			FillingDB.fillingDB();
		} catch (ClassNotFoundException e) {
			logger.error(e);		}

	}

	/*
	 *//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}
	  */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDaoImpl userDao = DaoFactory.getUserDaoImpl();
		try {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			if (request.getSession().getAttribute("login") != null) {
				if (!request.getSession().getAttribute("login").equals(login)) {
					request.getRequestDispatcher(selectNextPage(userDao.getUser().getUser_role_id())).forward(request,
							response);
				} else {
					if (userDao.validate(login, password)) {
						request.getSession().setAttribute("login", login);
						request.getSession().setAttribute("user_id", userDao.getUser().getUser_id());
						request.getSession().setAttribute("role", userDao.getUser().getUser_role_id());
						response.sendRedirect(selectNextPage(userDao.getUser().getUser_role_id()));
						// request.getRequestDispatcher(selectNextPage(userDao.getUser().getUser_role_id()))
						// .forward(request, response);
					} else {
						request.getSession().removeAttribute("login");
						request.getSession().removeAttribute("user_id");
						request.getSession().removeAttribute("role");
						response.sendRedirect("");
					}
				}
			} else {
				if (userDao.validate(login, password)) {
					request.getSession().setAttribute("login", login);
					request.getSession().setAttribute("user_id", userDao.getUser().getUser_id());
					request.getSession().setAttribute("role", userDao.getUser().getUser_role_id());
					response.sendRedirect(selectNextPage(userDao.getUser().getUser_role_id()));
					// request.getRequestDispatcher(selectNextPage(userDao.getUser().getUser_role_id())).forward(request,
					// response);
				} else {
					request.getSession().removeAttribute("login");
					request.getSession().removeAttribute("user_id");
					request.getSession().removeAttribute("role");
					response.sendRedirect("");
				}
			}
		} catch (NullPointerException e) {
			logger.error(e);
			request.getSession().removeAttribute("login");
			request.getSession().removeAttribute("user_id");
			request.getSession().removeAttribute("role");
			response.sendRedirect("");
		}
	}

	private String selectNextPage(Integer user_role_id) {
		switch (user_role_id) {
		case 1:
			return "adminPage";
		case 2:
			return "userPage";
		default:
			return "";
		}

	}
}
