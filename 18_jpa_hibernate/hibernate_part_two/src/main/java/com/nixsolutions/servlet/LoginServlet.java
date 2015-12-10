package com.nixsolutions.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.hibernate.DaoFactoryHibernate;
import com.nixsolutions.hibernate.entity.User;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -1714560100510914933L;
	private static UserDAO userDao;
	
	@Override
	public void init() {
		DaoFactoryHibernate daoFactory = new DaoFactoryHibernate();
		userDao = daoFactory.getUserDAO();
	}

/*	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			PrintWriter out = response.getWriter();
			generateOutput(user, out);
		} else {
			//
			response.sendRedirect("");
		}
	}*/

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		try {
			User user = userDao.getUserByLogin(login);
			if (password.equals(user.getUserPassword())) {
				request.getSession().setAttribute("login", login);
				response.sendRedirect("nav.do");
			} else {
				//
				response.sendRedirect("");
			}
		} catch (NullPointerException e) {
			//
			response.sendRedirect("");
		}
	}
}
