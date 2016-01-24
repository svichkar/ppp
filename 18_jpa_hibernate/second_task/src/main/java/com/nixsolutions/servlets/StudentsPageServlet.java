package com.nixsolutions.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.StatusDAO;
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.dao.StudentGroupDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Student;

@WebServlet("/students")
public class StudentsPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static StudentDAO studentDao;
	private static StudentGroupDAO groupDao;
	private static StatusDAO statusDao;
	private static TermDAO termDao;

	@Override
	public void init() throws ServletException {
		groupDao = DAOFactory.getStudentGroup();
		statusDao = DAOFactory.getStatus();
		termDao = DAOFactory.getTerm();
		studentDao = DAOFactory.getStudent();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = String.valueOf(request.getSession().getAttribute("role"));
		if ("manager".equals(role)) {
			request.setAttribute("students", studentDao.findAllStudents());
			request.setAttribute("groups", groupDao.findAllStudentGroups());
			request.setAttribute("statuses", statusDao.findAllStatuses());
			request.setAttribute("terms", termDao.findAllTerms());
			request.getRequestDispatcher("/WEB-INF/jsp/students.jsp").forward(request, response);
		} else {
			response.sendRedirect(
					"index.jsp?message=Your are not a manager. Please login as manager to continue work.");
		}
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
			Student newStudent = new Student();
			newStudent.setFirstName(request.getParameter("newFirstName"));
			newStudent.setLastName(request.getParameter("newLastName"));
			newStudent.setGroup(groupDao.findStudentGroupById(Long.valueOf(request.getParameter("newGroupName"))));
			newStudent.setAdmissionDate(Date.valueOf(request.getParameter("newAdmissionDate")));
			newStudent.setStatus(statusDao.findStatusById(Long.valueOf(request.getParameter("newStatusName"))));
			newStudent.setTerm(termDao.findTermById(Long.valueOf(request.getParameter("newTermName"))));
			studentDao.createStudent(newStudent);
			response.sendRedirect("students?message=Added student with id " + newStudent.getStudentId());
		}

		if (request.getParameter("search") != null) {
			String lastName = request.getParameter("searchLastName");
			String groupId = request.getParameter("searchGroupId");
			if (lastName != null && groupId != null) {
				Long groupIdLong = Long.valueOf(groupId);
				List<Student> searchStudents = studentDao.findStudentsByLastNameAndGroupId(lastName, groupIdLong);
				request.setAttribute("students", searchStudents);
			}

			if (lastName != null && groupId == null) {
				List<Student> searchStudents = studentDao.findStudentsByLastName(lastName);
				request.setAttribute("students", searchStudents);
			}

			if (groupId != null && lastName.isEmpty()) {
				Long groupIdLong = Long.valueOf(groupId);
				List<Student> searchStudents = studentDao.findStudentsByGroupId(groupIdLong);
				request.setAttribute("students", searchStudents);
			}
			request.setAttribute("groups", groupDao.findAllStudentGroups());
			request.getRequestDispatcher("/WEB-INF/jsp/students.jsp").forward(request, response);
		}
	}

}
