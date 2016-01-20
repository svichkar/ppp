package com.nixsolutions.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.bean.JournalBean;
import com.nixsolutions.studentgrade.bean.StudentBean;
import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.GradeDAO;
import com.nixsolutions.studentgrade.dao.JournalDAO;
import com.nixsolutions.studentgrade.dao.StatusDAO;
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.dao.StudentGroupDAO;
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.entity.Student;

@WebServlet("/journal")
public class JournalPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static StudentDAO studentDao;
	private static StudentGroupDAO groupDao;
	private static StatusDAO statusDao;
	private static TermDAO termDao;
	private static JournalDAO journalDao;
	private static SubjectDAO subjectDao;
	private static GradeDAO gradeDao;

	@Override
	public void init() throws ServletException {
		groupDao = DAOFactory.getStudentGroup();
		statusDao = DAOFactory.getStatus();
		termDao = DAOFactory.getTerm();
		studentDao = DAOFactory.getStudent();
		journalDao = DAOFactory.getJournal();
		subjectDao = DAOFactory.getSubject();
		gradeDao = DAOFactory.getGrade();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = String.valueOf(request.getSession().getAttribute("role"));
		if ("manager".equals(role)) {
			if (request.getParameter("student_id") != null) {
				Long studentId = Long.valueOf(request.getParameter("student_id"));
				Student student = studentDao.findStudentById(studentId);
				StudentBean viewStudent = this.getViewStudent(student);
				request.setAttribute("student", viewStudent);
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
				StudentBean viewStudent = this.getViewStudent(student);

				List<JournalBean> displayJournal = new ArrayList<>();
				List<Journal> journalList = journalDao.findJournalsByStudentIdAndTermId(studentId, termId);
				for (Journal j : journalList) {
					JournalBean journal = new JournalBean();
					journal.setTerm(termDao.findTermById(termId));
					journal.setSubject(subjectDao.findSubjectById(j.getSubjectId()));
					journal.setGrade(gradeDao.findGradeById(j.getGradeId()));
					journal.setGpa(journalDao.findGPAByStudentIdAndTermId(studentId, termId));
					displayJournal.add(journal);
				}
				request.setAttribute("journals", displayJournal);
				request.setAttribute("student", viewStudent);
				request.setAttribute("terms", termDao.findTermsByStudentId(studentId));
				request.getRequestDispatcher("/WEB-INF/jsp/journal.jsp").forward(request, response);
			} else
				response.sendRedirect("students?message=Please select student to view journal");
		}
	}

	private StudentBean getViewStudent(Student student) {
		StudentBean viewStudent = new StudentBean();
		viewStudent.setStudent(student);
		viewStudent.setGroup(groupDao.findStudentGroupById(student.getGroupId()));
		viewStudent.setStatus(statusDao.findStatusById(student.getStatusId()));
		viewStudent.setTerm(termDao.findTermById(student.getTermId()));
		return viewStudent;
	}

}
