package com.nixsolutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.User;

@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet{
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("welcome, " + request.getParameter("username") + " on the admin page");
		String start = "<html><body>"
				+ "<table>"
				+ "<tr>"
				+ "<th>user_id</th>"
				+ "<th>user_name</th>"
				+ "<th>user_password</th>"
				+ "<th>user_role</th>"
				+ "</tr>";
		
		List<User> users = factory.getUserDao().getAllUsers();
		
		StringBuilder usersTable = new StringBuilder(start);
		for (User user : users) {
			usersTable.append("<tr>");
			usersTable.append("<th contenteditable='true'>"+ user.getUserId() + "</th>");
			usersTable.append("<th contenteditable='true'>"+ user.getUserName() + "</th>");
			usersTable.append("<th contenteditable='true'>"+ user.getUserPassword() + "</th>");
			usersTable.append("<th contenteditable='true'>"+ user.getRoleId() + "</th>");
			usersTable.append("</tr>");
		}
		usersTable.append("</table></body></html>");
		out.print(usersTable.toString());
	}

}
