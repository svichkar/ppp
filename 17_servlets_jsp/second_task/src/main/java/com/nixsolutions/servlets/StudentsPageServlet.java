package com.nixsolutions.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.bean.StudentBean;
import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.StatusDAO;
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.dao.StudentGroupDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.entity.Term;

@WebServlet("/students")
public class StudentsPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static StudentDAO studentDao;
	private static StudentGroupDAO groupDao;
	private static StatusDAO statusDao;
	private static TermDAO termDao;
	private static StudentGroup group;
	private static Status status;
	private static Term term;

	@Override
	public void init() throws ServletException {
		groupDao = DAOFactory.getStudentGroup();
		statusDao = DAOFactory.getStatus();
		termDao = DAOFactory.getTerm();
		studentDao = DAOFactory.getStudent();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<StudentBean> displayStudentList = new ArrayList<>();
		List<Student> studentList = studentDao.findAllStudents();
		for (Student t : studentList) {
			group = groupDao.findStudentGroupById(t.getGroupId());
			status = statusDao.findStatusById(t.getStatusId());
			term = termDao.findTermById(t.getTermId());
			StudentBean student = new StudentBean(t, group, status, term);
			displayStudentList.add(student);
		}
		request.setAttribute("students", displayStudentList);
		request.setAttribute("groups", groupDao.findAllStudentGroups());
		request.setAttribute("statuses", statusDao.findAllStatuses());
		request.setAttribute("terms", termDao.findAllTerms());
		request.getRequestDispatcher("/WEB-INF/jsp/students.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("delete") != null) {
			Long studentId = Long.valueOf(request.getParameter("student_id"));
			studentDao.deleteStudent(studentDao.findStudentById(studentId));
			if (studentDao.findStudentById(studentId) == null) {
				response.sendRedirect("students?message=Deleted student with id " + studentId);
			} else
				response.sendRedirect(
						"students?message=Student is not deleted. Please delete associated journal records at first.");
		}
		if (request.getParameter("add") != null) {
			Long studentId = Long.valueOf(studentDao.findAllStudents().size() + 1);
			Student newStudent = new Student(studentId, request.getParameter("newFirstName"),
					request.getParameter("newLastName"), Long.valueOf(request.getParameter("newGroupName")),
					Date.valueOf(request.getParameter("newAdmissionDate")),
					Integer.valueOf(request.getParameter("newStatusName")),
					Long.valueOf(request.getParameter("newTermName")));
			studentDao.createStudent(newStudent);
			response.sendRedirect("students?message=Added student with id " + studentId);
		}

		if (request.getParameter("search") != null) {
			String lastName = request.getParameter("searchLastName");
			String groupId = request.getParameter("searchGroupId");
			if (lastName != null && groupId != null) {
				Long groupIdLong = Long.valueOf(groupId);
				List<Student> searchStudents = studentDao.findStudentsByLastNameAndGroupId(lastName, groupIdLong);
				List<StudentBean> searchStudentResult = this.displayStudentList(searchStudents);
				request.setAttribute("students", searchStudentResult);
			}

			if (lastName != null && groupId == null) {
				List<Student> searchStudents = studentDao.findStudentsByLastName(lastName);
				List<StudentBean> searchStudentResult = this.displayStudentList(searchStudents);
				request.setAttribute("students", searchStudentResult);
			}

			if (groupId != null && lastName.isEmpty()) {
				Long groupIdLong = Long.valueOf(groupId);
				List<Student> searchStudents = studentDao.findStudentsByGroupId(groupIdLong);
				List<StudentBean> searchStudentResult = this.displayStudentList(searchStudents);
				request.setAttribute("students", searchStudentResult);
			}
			request.setAttribute("groups", groupDao.findAllStudentGroups());
			request.setAttribute("statuses", statusDao.findAllStatuses());
			request.setAttribute("terms", termDao.findAllTerms());
			request.getRequestDispatcher("/WEB-INF/jsp/students.jsp").forward(request, response);
		}
	}

	private List<StudentBean> displayStudentList(List<Student> studentList) {
		List<StudentBean> displayStudentList = new ArrayList<>();
		for (Student t : studentList) {
			group = groupDao.findStudentGroupById(t.getGroupId());
			status = statusDao.findStatusById(t.getStatusId());
			term = termDao.findTermById(t.getTermId());
			StudentBean student = new StudentBean(t, group, status, term);
			displayStudentList.add(student);
		}
		return displayStudentList;
	}
}
