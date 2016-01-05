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
		//out.println("welcome, " + request.getParameter("username") + " on the admin page");
		response.setContentType("text/html");
		//out.println("<!DOCTYPE html>");
       // out.println("<html><head>");
      //  out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
       // out.println("<title>Auth page</title></head>");
		
		String start = "<html><body>"
				+ "<h1>welcome, " + request.getParameter("username") + " on the admin page</h1>"
				+ "<table>"
				+ "<tr>"
				+ "<td>user_id</td>"
				+ "<td>user_name</td>"
				+ "<td>user_password</td>"
				+ "<td>user_role</td>"
				+ "<td>action</td>"
				+ "</tr>";
		String updateButton = "<td><input type=submit value=\"edit user\" name=\"button\"></td>";
		String createButton = "<td><input type=submit value=\"create user\" name=\"button\"></td>";
		
		List<User> users = factory.getUserDao().getAllUsers();
		
		StringBuilder usersTable = new StringBuilder(start);
		for (User user : users) {
			usersTable.append("<tr>");
			usersTable.append("<form id=\"update\" action=\"createupdate\" method=\"post\">");
			usersTable.append("<td><input type=\"text\" name=\"userid\" value=\"" + user.getUserId() + "\" readonly/></td>");
			usersTable.append("<td><input type=\"text\" name=\"username\" value=\""+ user.getUserName() + "\"/></td>");
			usersTable.append("<td><input type=\"text\" name=\"password\" value=\""+ user.getUserPassword() + "\"/></td>");
			usersTable.append("<td><input type=\"text\" name=\"roleid\" value=\""+ user.getRoleId() + "\"/></td>");
			usersTable.append(updateButton);
			usersTable.append("</form></tr>");
		}
		usersTable.append("<form id=\"create\" action=\"createupdate\" method=\"post\">"
				+ "<tr>"
				+ "<td></td>"
				+ "<td><input type=\"text\" name=\"username\"/></td>"
				+ "<td><input type=\"text\" name=\"password\"/></td>"
				+ "<td><input type=\"text\" name=\"roleid\"/></td>" 
				+ createButton
				+ "</tr></form>");
		usersTable.append("</table></body></html>");
		out.print(usersTable.toString());
	}

}
