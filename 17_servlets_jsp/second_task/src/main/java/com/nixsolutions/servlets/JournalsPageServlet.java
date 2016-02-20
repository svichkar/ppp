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
import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.entity.Subject;

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
		List<JournalBean> displayJournal = new ArrayList<>();
		List<Journal> journalList = journalDao.findAllJournals();
		for (Journal j : journalList) {
			Student st = studentDao.findStudentById(j.getStudentId());
			Subject subject = subjectDao.findSubjectById(j.getSubjectId());
			Grade grade = gradeDao.findGradeById(j.getGradeId());
			StudentBean student = new StudentBean(st, groupDao.findStudentGroupById(st.getGroupId()));
			JournalBean journal = new JournalBean(j, student, subject, grade);
			displayJournal.add(journal);
		}
		List<StudentBean> displayStudentList = new ArrayList<>();
		List<Student> studentList = studentDao.findAllStudents();
		for (Student t : studentList) {
			StudentBean student = new StudentBean(t, groupDao.findStudentGroupById(t.getGroupId()));
			displayStudentList.add(student);
		}

		request.setAttribute("journals", displayJournal);
		request.setAttribute("students", displayStudentList);
		request.setAttribute("subjects", subjectDao.findAllSubjects());
		request.setAttribute("grades", gradeDao.findAllGrades());
		request.getRequestDispatcher("/WEB-INF/jsp/journals.jsp").forward(request, response);
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
