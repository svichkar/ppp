package com.nixsolutions.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.UserDAOImpl;
import com.nixsolutions.entities.User;

/**
 * Servlet implementation class LogoutSrvt
 */
public class LogoutSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(LogoutSrvt.class);
	private UserDAOImpl userImpl;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutSrvt() {
		try {
			userImpl = ServiceStationDAOFactoryImpl.getUserDao();
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		session.removeAttribute("login");
		/// update db
		User foundUser = userImpl.findByName(login);
		if (foundUser.getUsername() != null) {
			try {
				foundUser.setSession("");;
				userImpl.update(foundUser);
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
