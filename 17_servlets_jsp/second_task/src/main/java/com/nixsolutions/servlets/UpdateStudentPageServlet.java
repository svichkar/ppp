package com.nixsolutions.servlets;

import java.io.IOException;
import java.sql.Date;

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

@WebServlet("/updateStudent")
public class UpdateStudentPageServlet extends HttpServlet {

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
		if (request.getParameter("student_id") != null) {
			Long studentId = Long.valueOf(request.getParameter("student_id"));
			Student updateStudent = studentDao.findStudentById(studentId);
			request.setAttribute("updateStudent", updateStudent);
			request.setAttribute("groups", groupDao.findAllStudentGroups());
			request.setAttribute("statuses", statusDao.findAllStatuses());
			request.setAttribute("terms", termDao.findAllTerms());
			request.getRequestDispatcher("/WEB-INF/jsp/updateStudent.jsp").forward(request, response);
		} else
			response.sendRedirect("students?message=Please select student for update");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			Long studentId = Long.valueOf(request.getParameter("student_id"));
			Student updatedStudent = new Student(studentId, request.getParameter("updatedFirstName"),
					request.getParameter("updatedLastName"), Long.valueOf(request.getParameter("updatedGroupName")),
					Date.valueOf(request.getParameter("updatedAdmissionDate")),
					Integer.valueOf(request.getParameter("updatedStatusName")),
					Long.valueOf(request.getParameter("updatedTermName")));
			studentDao.updateStudent(updatedStudent);
			response.sendRedirect("students?message=Updated student with id " + studentId);
		}

	}
}
