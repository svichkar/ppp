package servlets;

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

import dao.UsersFactory;
import entity.Users;

@WebServlet(name = "addUser", urlPatterns = "/adduser")
public class AddNewUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Users> usersList = null;
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String role = request.getParameter("select");

		UsersFactory users = new UsersFactory();
		
		users.addNewRow(Arrays.asList("login", "password", "role"),
				Arrays.asList(login, password, role));
		usersList = users.find(null, null);
		PagesVisualization pv = new PagesVisualization();
		PrintWriter out = response.getWriter();
		pv.usersList(out, usersList);

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
