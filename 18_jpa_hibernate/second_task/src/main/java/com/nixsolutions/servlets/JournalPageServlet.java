package com.nixsolutions.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.GradeDAO;
import com.nixsolutions.studentgrade.dao.JournalDAO;
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.entity.Student;

@WebServlet("/journal")
public class JournalPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static StudentDAO studentDao;
	private static TermDAO termDao;
	private static JournalDAO journalDao;
	private static GradeDAO gradeDao;

	@Override
	public void init() throws ServletException {
		termDao = DAOFactory.getTerm();
		studentDao = DAOFactory.getStudent();
		journalDao = DAOFactory.getJournal();
		gradeDao = DAOFactory.getGrade();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = String.valueOf(request.getSession().getAttribute("role"));
		if ("manager".equals(role)) {
			if (request.getParameter("student_id") != null) {
				Long studentId = Long.valueOf(request.getParameter("student_id"));
				Student student = studentDao.findStudentById(studentId);
				request.setAttribute("student", student);
				request.setAttribute("terms", termDao.findTermsByStudentId(studentId));
				request.getRequestDispatcher("/WEB-INF/jsp/journal.jsp").forward(request, response);
			} else
				response.sendRedirect("students?message=Please select student to view journal");

		} else {
			response.sendRedirect(
					"index.jsp?message=Your are not a manager. Please login as manager to continue work.");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("viewJournal") != null) {
			if (request.getParameter("student_id") != null) {
				Long studentId = Long.valueOf(request.getParameter("student_id"));
				Long termId = Long.valueOf(request.getParameter("term_id"));
				Student student = studentDao.findStudentById(studentId);
				List<Journal> journalList = journalDao.findJournalsByStudentIdAndTermId(studentId, termId);
				request.setAttribute("journals", journalList);
				request.setAttribute("gpas", gradeDao.findGradeById(journalDao.findGPAByStudentIdAndTermId(studentId, termId)));
				request.setAttribute("student", student);
				request.setAttribute("terms", termDao.findTermsByStudentId(studentId));
				request.getRequestDispatcher("/WEB-INF/jsp/journal.jsp").forward(request, response);
			} else
				response.sendRedirect("students?message=Please select student to view journal");
		}
	}
}
