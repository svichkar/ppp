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
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.entity.Student;

@WebServlet("/updateJournal")
public class UpdateJournalPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static JournalDAO journalDao;
	private static SubjectDAO subjectDao;
	private static GradeDAO gradeDao;
	private static StudentDAO studentDao;

	@Override
	public void init() throws ServletException {
		journalDao = DAOFactory.getJournal();
		subjectDao = DAOFactory.getSubject();
		gradeDao = DAOFactory.getGrade();
		studentDao = DAOFactory.getStudent();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = String.valueOf(request.getSession().getAttribute("role"));
		if ("manager".equals(role)) {
			if (request.getParameter("journal_id") != null) {
				Long journalId = Long.valueOf(request.getParameter("journal_id"));
				Journal updateJournal = journalDao.findJournalById(journalId);
				List<Student> studentList = studentDao.findAllStudents();
				request.setAttribute("updateJournal", updateJournal);
				request.setAttribute("students", studentList);
				request.setAttribute("subjects", subjectDao.findAllSubjects());
				request.setAttribute("grades", gradeDao.findAllGrades());
				request.getRequestDispatcher("/WEB-INF/jsp/updateJournal.jsp").forward(request, response);
			} else
				response.sendRedirect("journals?message=Please select journal record for update");
		} else {
			response.sendRedirect(
					"index.jsp?message=Your are not a manager. Please login as manager to continue work.");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			Long journalId = Long.valueOf(request.getParameter("journal_id"));
			Journal updatedJournal = journalDao.findJournalById(journalId);
			updatedJournal.setStudent(studentDao.findStudentById(Long.valueOf(request.getParameter("updatedStudent"))));
			updatedJournal.setSubject(subjectDao.findSubjectById(Long.valueOf(request.getParameter("updatedSubject"))));
			updatedJournal.setGrade(gradeDao.findGradeById(Long.valueOf(request.getParameter("updatedGrade"))));
			journalDao.updateJournal(updatedJournal);
			response.sendRedirect("journals?message=Updated journal record with id " + updatedJournal.getJournalId());
		}
	}
}
