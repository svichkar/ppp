package com.nixsolutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.impl.GradeDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.JournalDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.RoleDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.StudentDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.SubjectDAOImpl;
import com.nixsolutions.studentgrade.dao.impl.UserDAOImpl;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.entity.User;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String login = String.valueOf(request.getSession().getAttribute("login"));
		if (login!=null) {
			UserDAOImpl userDao = DAOFactory.getUser();
			User user = userDao.findUserByLogin(login.toLowerCase());
			getMainPage(user, out);			
		}		
		else {
			out.println("<html>"
					+ "<head><title>Redirect Page</title></head>"
					+ "<body>"
					+ "<p>Please login to application.</p>"
					+ "<p><a href=\"index.html\">Login Page</a></p>"
					+ "</body>"
					+ "</html>");			
		}
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		UserDAOImpl userDao = DAOFactory.getUser();
		User user = userDao.findUserByLogin(login.toLowerCase());
		if (user != null && password.equals(user.getPassword())) {			
			request.getSession().setAttribute("login", login); 
			getMainPage(user, out);			
		} else {
			out.println("<html>"
					+ "<head><title>Redirect Page</title></head>"
					+ "<body>"
					+ "<p>Your login or password is incorrect. Please go to login page and try again.</p>"
					+ "<p><a href=\"index.html\">Login Page</a></p>"
					+ "</body>"
					+ "</html>");
		}
		out.close();	
		
	}
	
	private void getMainPage(User user, PrintWriter out) {
		RoleDAOImpl roleDao = DAOFactory.getRole();
		Role role = roleDao.findRoleById(user.getRoleId());
		out.println("<html>"
				+ "<head><title>Main Page</title></head>"
				+ "<body>"
				+ "<p>You are logged in as <strong>" + role.getRoleName() + "</strong> user.</p>"
				+ "<form action=\"logout\" method=\"get\">"
				+ "<input type=\"submit\" name=\"Log out\" value=\"Log out\"></form>");
		if (("admin").equals(role.getRoleName()))
			out.println("<p>Please go to admin page.</p>"
					+ "<form action=\"admin\" method=\"get\">"
					+ "<input type=\"submit\" name=\"Go to Admin Page\" value=\"Go to Admin Page\">"
					+ "</form>");
		out.println("<h2>Journal</h2>"
				+ "<table border=\"1\"><tr><th>JOURNAL_ID</th><th>STUDENT</th><th>SUBJECT</th><th>GRADE</th></tr>");
		
		JournalDAOImpl journalDao = DAOFactory.getJournal();
		StudentDAOImpl studentDao = DAOFactory.getStudent();
		SubjectDAOImpl subjectDao = DAOFactory.getSubject();
		GradeDAOImpl gradeDao = DAOFactory.getGrade();
		List<Journal> journal = journalDao.findAllJournals();
		for (Journal j : journal) {
			out.print("<tr><td>" + j.getJournalId() + "</td>"
                    + "<td>" + studentDao.findStudentById(j.getStudentId()).getFirstName()
                    + " " + studentDao.findStudentById(j.getStudentId()).getLastName()+ "</td>"
                    + "<td>" + subjectDao.findSubjectById(j.getSubjectId()).getSubjectName() + "</td>"
                    + "<td>" + gradeDao.findGradeById(j.getGradeId()).getGradeName() + "</td></tr>");
			}
		
		out.println("</table><br />"
				+ "</body>"
				+ "</html>");
	}
}
