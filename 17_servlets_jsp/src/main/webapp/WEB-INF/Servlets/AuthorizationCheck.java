package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DbDAO.Users;

@WebServlet(name = "AuthorizationServlet", urlPatterns = "/j_security_check")
public class AuthorizationCheck extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		PagesVisualization pv = new PagesVisualization();
		String login = request.getParameter("username");
		String password = request.getParameter("password");
		Users users = new Users();

		List<Users> user = null;
		try {
			user = users.findElement(new String[]{"password", "role"},
					"login='" + login + "';");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user.size() != 0) {
			for (Users account : user) {
				if (account.getPassword().equals(password)) {
					pv.mainPage(out, account.getRole());

				} else {
					out.println(
							"<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
					out.println(
							"<BODY>Your login and password are not valid.<BR>");
					out.println("</BODY></HTML>");
				}
			}
		} else {
			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			out.println("<BODY>Your login and password are not valid.<BR>");
			out.println("</BODY></HTML>");
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

}
