package servlets;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import dao.CategoryFactory;
import entity.Book;
import entity.Users;

public class PagesVisualization {

	public void mainPage(PrintWriter out, String role) {
		if (role.equals("admin")) {
			out.println("<!doctype html>");
			out.println("<html>");
			out.println("<head>");
			out.println("	<title>Library</title>");
			out.println("</head>");
			out.println("<body>");
			out.println(
					"<p style=\"text-align: center;\"><span style=\"font-size: 36px;\">&nbsp;Welcome to the library control center</span></p>");
			out.println("");
			out.println(
					"<form action=\"search\" enctype=\"text/plain\" id=\"search\" method=\"get\" name=\"search\" target=\"_self\">");
			out.println(
					"<p style=\"text-align: center;\"><input maxlength=\"100\" name=\"search_bar\" size=\"50\" type=\"text\" /><input name=\"performsearch\" type=\"submit\" value=\"Go\" /></p>");
			out.println("</form>");
			out.println("");
			out.println(
					"<form action=\"admin_tool\" enctype=\"text/plain\" id=\"admin_tool\" method=\"get\" name=\"Admin tool\" target=\"_self\"><input name=\"AdminTool\" type=\"submit\" value=\"Admin tool\" />&nbsp;</form>");
			out.println("<p>&nbsp;</p>");
			out.println("");
			out.println(
					"<form action=\"getBooks\" enctype=\"text/plain\" method=\"get\" name=\"all_books\" target=\"_self\"><input name=\"book_list\" type=\"submit\" value=\"All books\" />&nbsp;</form>");
			out.println("<p>&nbsp;</p>");
			out.println(
					"<form action=\"logout\" enctype=\"text/plain\" id=\"logout\" method=\"get\" name=\"logout\" target=\"_self\"><input name=\"logout\" type=\"submit\" value=\"LOGOUT\" />&nbsp;</form>");
			out.println("</body>");
			out.println("</html>");
		} else {
			out.println("<!doctype html>");
			out.println("<html>");
			out.println("<head>");
			out.println("	<title>Library</title>");
			out.println("</head>");
			out.println("<body>");
			out.println(
					"<p style=\"text-align: center;\"><span style=\"font-size: 36px;\">&nbsp;Welcome to the library control center</span></p>");
			out.println("");
			out.println(
					"<form action=\"search\" enctype=\"text/plain\" id=\"search\" method=\"get\" name=\"search\" target=\"_self\">");
			out.println(
					"<p style=\"text-align: center;\"><input maxlength=\"100\" name=\"search_bar\" size=\"50\" type=\"text\" /><input name=\"performsearch\" type=\"submit\" value=\"Go\" /></p>");
			out.println("</form>");
			out.println("");
			out.println("");
			out.println(
					"<form action=\"getBooks\" enctype=\"text/plain\" method=\"get\" name=\"all_books\" target=\"_self\"><input name=\"book_list\" type=\"submit\" value=\"All books\" />&nbsp;</form>");
			out.println("<p>&nbsp;</p>");
			out.println(
					"<form action=\"logout\" enctype=\"text/plain\" id=\"logout\" method=\"get\" name=\"logout\" target=\"_self\"><input name=\"logout\" type=\"submit\" value=\"LOGOUT\" />&nbsp;</form>");
			out.println("</body>");
			out.println("</html>");
		}
	}
	public void logout(PrintWriter out) {
		out.println(
				"<!DOCTYPE html><html><body>	<h2>Login page</h2>		<form action=\"j_security_check\" method=post>		<p>			<strong>Please enter Your login: </strong>			<input type=\"text\" name=\"username\" size=\"25\">		<p>		<p>			<strong>Please enter Your password: </strong>			<input type=\"password\" size=\"15\" name=\"password\">		<p>		<p>			<input type=\"submit\" value=\"Submit\">			<input type=\"reset\" value=\"Reset\">	</form></body></html>");

	}

	public void usersList(PrintWriter out, List<Users> users) {
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>Library</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(
				"<p style=\"text-align: center;\"><span style=\"font-size: 36px;\">&nbsp;Admin tool</span></p>");
		out.println("");
		out.println(
				"<form action=\"search\" enctype=\"text/plain\" id=\"search\" method=\"get\" name=\"search\" target=\"_self\">");
		out.println(
				"<p style=\"text-align: center;\"><input maxlength=\"100\" name=\"search_bar\" size=\"50\" type=\"text\" /><input name=\"performsearch\" type=\"submit\" value=\"Go\" /></p>");
		out.println("</form>");
		out.println("");
		out.println(
				"<table align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px;\">");
		out.println("	<caption>Users</caption>");
		out.println("	<tbody>");
		out.println("<tr>");

		out.println("<th>Delete?</th>");
		out.println("<th>LOGIN</th>");
		out.println("<th>PASSWORD</th>");
		out.println("<th>ROLE</th>");

		out.println("<tr>");
		out.println("<tr>");

		out.println("</tr>");
		for (Users user : users) {
			out.println("<tr>");
			out.println("<form action=\"updateUser\" method=\"post\">");
			out.println(
					"<td><input type=\"checkbox\" name=\"checkboxstate\" /> </td>");
			out.println(
					"<td><input maxlength=\"20\" name=\"login\"  type=\"text\" value=\""
							+ user.getLogin() + "\" /></td>");
			out.println(
					"<td><input maxlength=\"20\" name=\"password\"  type=\"text\" value=\""
							+ user.getPassword() + "\" /></td>");
			// out.println("<td><input maxlength=\"20\" name=\"role\"
			// type=\"text\" value=\""+ user.getRole()+"\" /></td>");
			switch (user.getRole()) {
				case "admin" :
					out.println(
							"<td><select name=\"select\"><option value=\"admin\">admin</option><option value=\"user\">user</option></select></td> ");
					break;
				case "user" :
					out.println(
							"<td><select name=\"select\"><option value=\"user\">user</option><option value=\"admin\">admin</option></select></td> ");
					break;
			}

			out.println(
					"<td><input type=\"submit\" value=\"Update\" style=\"width:100%\"></td>");
			out.println("<input name=\"userid\"  type=\"hidden\" value=\""
					+ user.getId() + "\" />");
			out.println("</form>");
			out.println("</tr>");

		}
		out.println("<tr>");
		out.println("<form action=\"addUser\" method=\"post\">");
		out.println("<td></td>");
		out.println(
				"<td><input maxlength=\"20\" name=\"login\"  type=\"text\" value=\"\" /></td>");
		out.println(
				"<td><input maxlength=\"20\" name=\"password\"  type=\"text\" value=\"\" /></td>");
		out.println(
				"<td><select name=\"select\"><option value=\"user\">user</option><option value=\"admin\">admin</option></select></td> ");
		out.println(
				"<td><input type=\"submit\" value=\"ADD NEW\" style=\"width:100%\"></td>");
		out.println("</form>");
		out.println("</tr>");
		out.println("</tbody>");
		out.println("</table>");
		out.println("");
		out.println(
				"<form action=\"getBooks\" enctype=\"text/plain\" method=\"get\" name=\"all_books\" target=\"_self\"><input name=\"book_list\" type=\"submit\" value=\"All books\" />&nbsp;</form>");
		out.println("");
		out.println("<p>&nbsp;</p>");
		out.println("");
		out.println(
				"<form action=\"logout\" enctype=\"text/plain\" id=\"logout\" method=\"get\" name=\"logout\" target=\"_self\"><input name=\"logout\" type=\"submit\" value=\"LOGOUT\" />&nbsp;</form>");
		out.println("</body>");
		out.println("</html>");

	}
	public void bookList(PrintWriter out, List<Book> list) {
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>Library</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(
				"<p style=\"text-align: center;\"><span style=\"font-size: 36px;\">&nbsp;Admin tool</span></p>");
		out.println("");
		out.println(
				"<form action=\"search\" enctype=\"text/plain\" id=\"search\" method=\"get\" name=\"search\" target=\"_self\">");
		out.println(
				"<p style=\"text-align: center;\"><input maxlength=\"100\" name=\"search_bar\" size=\"50\" type=\"text\" /><input name=\"performsearch\" type=\"submit\" value=\"Go\" /></p>");
		out.println("</form>");
		out.println("");
		out.println(
				"<table align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px;\">");
		out.println("	<caption>Users</caption>");
		out.println("	<tbody>");
		out.println("<tr>");

		out.println("<th>Name</th>");
		out.println("<th>Author</th>");
		out.println("<th>Publisher</th>");
		out.println("<th>Catergory</th>");

		out.println("<tr>");
		out.println("<tr>");

		out.println("</tr>");
		CategoryFactory cf = new CategoryFactory();
		int i = 1;
		for (Book book : list) {
			out.println("<tr>");
			out.println("<td>" + i++ + "</td>");
			out.println("<td>" + book.getName() + "</td>");
			out.println("<td>" + book.getAuthor() + "</td>");
			out.println("<td>" + book.getPublisher() + "</td>");
			String category = cf
					.find(new String[]{"name"}, "id=" + book.getCategory_id())
					.get(0).getName();
			out.println("<td>" + category + "</td>");

			out.println("</tr>");
		}

		out.println("</tr>");
		out.println("</tbody>");
		out.println("</table>");
		out.println("");
		out.println(
				"<form action=\"getBooks\" enctype=\"text/plain\" method=\"get\" name=\"all_books\" target=\"_self\"><input name=\"book_list\" type=\"submit\" value=\"All books\" />&nbsp;</form>");
		out.println("");
		out.println("<p>&nbsp;</p>");
		out.println("");
		out.println(
				"<form action=\"logout\" enctype=\"text/plain\" id=\"logout\" method=\"get\" name=\"logout\" target=\"_self\"><input name=\"logout\" type=\"submit\" value=\"LOGOUT\" />&nbsp;</form>");
		out.println("</body>");
		out.println("</html>");

	}

}
