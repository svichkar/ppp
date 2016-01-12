package com.nixsolutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.User;

@SuppressWarnings("serial")
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		LOG.entry(request.getParameter("username"), request.getParameter("userpass"));
		String usr = request.getParameter("username");
		String pswd = request.getParameter("userpass");
		PrintWriter out = response.getWriter();
		User user = factory.getUserDao().getUserByNameAndPswd(usr, pswd);
		HttpSession session = request.getSession();

		response.setContentType("text/html");
		if (user != null) {
			if (user.getRoleId().equals(1)) {
				session.setAttribute("usrRole", "admin");
				session.setAttribute("usrName", usr);
				response.sendRedirect("admin");
			} else {
				session.setAttribute("usrRole", "regular");
				session.setAttribute("usrName", usr);
				response.sendRedirect("librarian");
			}
		} else {
			out.print("<p style=\"color:red\">you have entered incorrect user name or password</p>");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		out.close();
	}
}
