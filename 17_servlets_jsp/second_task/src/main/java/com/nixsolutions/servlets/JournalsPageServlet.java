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
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.dao.StudentGroupDAO;
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
	private static StudentGroupDAO groupDao;

	@Override
	public void init() throws ServletException {
		studentDao = DAOFactory.getStudent();
		groupDao = DAOFactory.getStudentGroup();
		journalDao = DAOFactory.getJournal();
		subjectDao = DAOFactory.getSubject();
		gradeDao = DAOFactory.getGrade();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = String.valueOf(request.getSession().getAttribute("role"));
		if ("manager".equals(role)) {
			List<JournalBean> displayJournal = new ArrayList<>();
			List<Journal> journalList = journalDao.findAllJournals();
			for (Journal j : journalList) {
				JournalBean journal = new JournalBean();
				StudentBean student = new StudentBean();
				Student st = studentDao.findStudentById(j.getStudentId());
				student.setStudent(st);
				student.setGroup(groupDao.findStudentGroupById(st.getGroupId()));
				journal.setJournal(j);
				journal.setStudent(student);
				journal.setSubject(subjectDao.findSubjectById(j.getSubjectId()));
				journal.setGrade(gradeDao.findGradeById(j.getGradeId()));
				displayJournal.add(journal);
			}
			List<StudentBean> displayStudentList = new ArrayList<>();
			List<Student> studentList = studentDao.findAllStudents();
			for (Student t : studentList) {
				StudentBean student = new StudentBean();
				student.setStudent(t);
				student.setGroup(groupDao.findStudentGroupById(t.getGroupId()));
				displayStudentList.add(student);
			}

			request.setAttribute("journals", displayJournal);
			request.setAttribute("students", displayStudentList);
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
			Long journalId = Long.valueOf(journalDao.findAllJournals().size() + 1);
			Journal newJournal = new Journal(journalId, Long.valueOf(request.getParameter("newStudent")),
					Long.valueOf(request.getParameter("newSubjectName")),
					Integer.valueOf(request.getParameter("newGradeName")));
			journalDao.createJournal(newJournal);
			response.sendRedirect("journals?message=Added journal record with id " + journalId);
		}
	}
}
