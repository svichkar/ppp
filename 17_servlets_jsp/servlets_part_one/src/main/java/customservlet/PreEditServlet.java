package customservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/preEdit.do")
public class PreEditServlet extends HttpServlet {

	private static final long serialVersionUID = -6865168161751461748L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><title>Add new user</title></head><body bgcolor=\"#F0FFFF\">" +
				"<div align=\"center\"><h1> Add new user </h1></div>");
		sb.append("<form action=\"edit.do\" method=\"POST\">");
		sb.append("Login: <input type=\"text\" name=\"login\"><br>");
		sb.append("Password: <input type=\"text\" name=\"password\"><br>");
		sb.append("Role: <select name=\"role\">");
		sb.append("<option disabled>Select role</option>");
		sb.append("<option value=\"1\">Administrator</option>");
		sb.append("<option value=\"2\">User</option>");
		sb.append("</select><br>");
		sb.append("<input type=\"submit\" value=\"Submit\">");
		sb.append("</form>");
		sb.append("</div></body></html>");
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String login = request.getParameter("login");
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><title>Add new user</title></head><body bgcolor=\"#F0FFFF\">" +
				"<div align=\"center\"><h1> Add new user </h1></div>");
		sb.append("<form action=\"edit.do\" method=\"POST\">");
		sb.append("Current Login: <input readonly type=\"text\" name=\"prev_login\" value=\"" + login + "\"><br>");
		sb.append("New Login: <input type=\"text\" name=\"login\"><br>");
		//sb.append("Previous Password: <input readonly type=\"text\" name=\"prev_password\"><br>");
		sb.append("Password: <input type=\"text\" name=\"password\"><br>");
		sb.append("Role: <select name=\"role\">");
		sb.append("<option disabled>Select role</option>");
		sb.append("<option value=\"1\">Administrator</option>");
		sb.append("<option value=\"2\">User</option>");
		sb.append("</select><br>");
		sb.append("<input type=\"submit\" value=\"Submit\">");
		sb.append("</form>");
		sb.append("</div></body></html>");
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
	}
}
