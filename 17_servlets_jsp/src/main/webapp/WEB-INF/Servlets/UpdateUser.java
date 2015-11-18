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

@WebServlet(name = "updateUser", urlPatterns = "/updateuser")
public class UpdateUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Users user;
		String[] checkBoxState = request.getParameterValues("checkboxstate");

		if (checkBoxState == null) {

			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String role = request.getParameter("select");
			String id = request.getParameter("userid");
			try {
				user = new Users();
				user.update(Arrays.asList("login", "password", "role"),
						Arrays.asList(login, password, role), "id=" + id);
				List<Users> usersList = user.findElement(null, null);
				PagesVisualization pv = new PagesVisualization();
				PrintWriter out = response.getWriter();
				pv.usersList(out, usersList);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			if (checkBoxState[0].toLowerCase().equals("on")) {
				String id = request.getParameter("userid");

				try {
					user = new Users();
					user.delete("id=" + id);
					List<Users> usersList = user.findElement(null, null);
					PagesVisualization pv = new PagesVisualization();
					PrintWriter out = response.getWriter();
					pv.usersList(out, usersList);
				} catch (SQLException e) {

					e.printStackTrace();
				}

			}
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("GET IS OK ");

	}

}
