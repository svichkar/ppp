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

@WebServlet("/journals")
public class JournalsPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static StudentDAO studentDao;
	private static JournalDAO journalDao;
	private static SubjectDAO subjectDao;
	private static GradeDAO gradeDao;

	@Override
	public void init() throws ServletException {
		studentDao = DAOFactory.getStudent();
		journalDao = DAOFactory.getJournal();
		subjectDao = DAOFactory.getSubject();
		gradeDao = DAOFactory.getGrade();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = String.valueOf(request.getSession().getAttribute("role"));
		if ("manager".equals(role)) {
			List<Journal> journalList = journalDao.findAllJournals();
			List<Student> studentList = studentDao.findAllStudents();
			request.setAttribute("journals", journalList);
			request.setAttribute("students", studentList);
			request.setAttribute("subjects", subjectDao.findAllSubjects());
			request.setAttribute("grades", gradeDao.findAllGrades());
			request.getRequestDispatcher("/WEB-INF/jsp/journals.jsp").forward(request, response);
		} else {
			response.sendRedirect(
					"index.jsp?message=Your are not a manager. Please login as manager to continue work.");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("delete") != null) {
			Long journalId = Long.valueOf(request.getParameter("journal_id"));
			journalDao.deleteJournal(journalDao.findJournalById(journalId));
			if (journalDao.findJournalById(journalId) == null) {
				response.sendRedirect("journals?message=Deleted journal with id " + journalId);
			} else
				response.sendRedirect("journals?message=Journal is not deleted.");
		}
		if (request.getParameter("add") != null) {
			Journal newJournal = new Journal();
			newJournal.setStudent(studentDao.findStudentById(Long.valueOf(request.getParameter("newStudent"))));
			newJournal.setSubject(subjectDao.findSubjectById(Long.valueOf(request.getParameter("newSubjectName"))));
			newJournal.setGrade(gradeDao.findGradeById(Long.valueOf(request.getParameter("newGradeName"))));
			journalDao.createJournal(newJournal);
			response.sendRedirect("journals?message=Added journal record with id " + newJournal.getJournalId());
		}
	}
}
