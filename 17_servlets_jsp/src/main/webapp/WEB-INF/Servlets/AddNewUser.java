package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DbDAO.Users;

@WebServlet(name = "addUser", urlPatterns = "/adduser")
public class AddNewUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Users> usersList = null;
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String role = request.getParameter("select");

		Users users = new Users();
		try {
			users.create(Arrays.asList("login", "password", "role"),
					Arrays.asList(login, password, role));
			usersList = users.findElement(null, null);
			PagesVisualization pv = new PagesVisualization();
			PrintWriter out = response.getWriter();
			pv.usersList(out, usersList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				usersList = users.findElement(null, null);
				PagesVisualization pv = new PagesVisualization();
				PrintWriter out = response.getWriter();
				pv.usersList(out, usersList);

				if (e.getMessage().toLowerCase().contains("unique")) {
					out.println(
							"<script>alert(\"Please choose another name.\")</script>");
				} else {
					out.println(
							"<script>alert(" + e.getMessage() + ")</script>");
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
