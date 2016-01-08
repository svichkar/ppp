package com.nixsolutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.H2DaoFactory;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet{
	private static final Logger LOG = LogManager.getLogger();
	private H2DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.H2);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		LOG.entry(request.getSession().getAttribute("usrRole"));
		PrintWriter out = response.getWriter();
		List<User> users;
		List<Role> allRoles = factory.getRoleDao().getAllRoles();
		String updateButton = "<td><input type=submit value=\"edit user\" name=\"button\"></td>";
		String createButton = "<td><input type=submit value=\"create user\" name=\"button\"></td>";
		String deleteButton = "<td><input type=submit value=\"delete user\" name=\"button\"></td>";

if (request.getSession(false) == null
|| request.getSession().getAttribute("usrRole") == null
|| !request.getSession().getAttribute("usrRole")
		.equals("admin")){
			out.print(
					"<p style=\"color:red\">you are not authorized to be here</p>"); 
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}else{
			users = factory.getUserDao().getAllUsers();
		response.setContentType("text/html");
		StringBuilder usersTable = new StringBuilder();
		users = factory.getUserDao().getAllUsers();
		usersTable.append("<head><title>Admin page</title></head>"
				+ "<body>"
				+ "<h1>Welcome to the Admin page</h1>"
				+ "<p> Hi, " +request.getSession().getAttribute("usrName") +"!</p>"
				+ "<table>"
				+ "<tr>"
				+ "<td>user_id</td>"
				+ "<td>user_name</td>"
				+ "<td>user_password</td>"
				+ "<td>user_role</td>"
				+ "<td>action</td>"
				+ "</tr>");		
		for (User user : users) {
			Role userRole = factory.getRoleDao().getRoleById(user.getRoleId());
			usersTable.append("<tr>");
			usersTable.append("<form id=\"update\" action=\"createupdate\" method=\"post\">");
			usersTable.append("<td><input type=\"text\" name=\"userid\" value=\"" + user.getUserId() + "\" readonly/></td>");
			usersTable.append("<td><input type=\"text\" name=\"username\" value=\""+ user.getUserName() + "\"/></td>");
			usersTable.append("<td><input type=\"text\" name=\"password\" value=\""+ user.getUserPassword() + "\"/></td>");
			if(userRole.getName().equals("admin")){
				usersTable.append("<td><input type=\"text\" name=\"selectrole\" value=\"admin\" readonly/></td>");
			}else{
			usersTable.append("<td><select name=\"selectrole\">");
			for (Role role : allRoles) {
				if(userRole.getName().equals(role.getName())){	
					usersTable.append("<option selected value=\"" + role.getName() +"\">"+ role.getName()+ "</option>");
				} else {
					usersTable.append("<option value=\"" + role.getName() +"\">"+ role.getName()+ "</option>");
				}
			}
			usersTable.append("</select></td>");
			}
			if(userRole.getName().equals("regular")){
				usersTable.append(updateButton + deleteButton);
			}	else {
				usersTable.append(updateButton);
			}
			usersTable.append("</form></tr>");
		}
		usersTable.append("<form id=\"create\" action=\"createupdate\" method=\"post\">"
				+ "<tr>"
				+ "<td></td>"
				+ "<td><input type=\"text\" name=\"username\"/></td>"
				+ "<td><input type=\"text\" name=\"password\"/></td>");
		usersTable.append("<td><select name=\"selectrole\">");
		usersTable.append("<option disabled selected>choose</option>");
		for (Role role : allRoles) {
			usersTable.append("<option value=\"" + role.getName() +"\">"+ role.getName()+ "</option>");
		}
		usersTable.append("</select></td>");
		usersTable.append(createButton
				+ "</tr></form></table></body>");
		out.print(usersTable.toString());
	}
	}
	
}
