package com.nixsolutions.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.util.HibernateUtil;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -1714560100510914933L;
	private static SessionFactory sf;
	private Session session;
	
	@Override
	public void init() {
		sf = HibernateUtil.getSessionFactory();
		session = sf.getCurrentSession();
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
		Transaction tr = session.beginTransaction();
		try {
			User user = (User) session.createCriteria(User.class).add(Restrictions.eq("userLogin", login)).list().get(0);
			if (password.equals(user.getUserPassword())) {
				request.getSession().setAttribute("login", login);
				response.sendRedirect("nav.do");
			} else {
				//
				response.sendRedirect("");
			}
		} catch (NullPointerException e) {
			//
			tr.rollback();
			response.sendRedirect("");
		}
	}
}
