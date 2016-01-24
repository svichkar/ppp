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
		String role = String.valueOf(request.getSession().getAttribute("role"));
		if ("manager".equals(role)) {
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
		} else {
			response.sendRedirect(
					"index.jsp?message=Your are not a manager. Please login as manager to continue work.");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			Long studentId = Long.valueOf(request.getParameter("student_id"));
			Student updatedStudent = studentDao.findStudentById(studentId);
			updatedStudent.setFirstName(request.getParameter("updatedFirstName"));
			updatedStudent.setLastName(request.getParameter("updatedLastName"));
			updatedStudent.setGroup(groupDao.findStudentGroupById(Long.valueOf(request.getParameter("updatedGroupName"))));
			updatedStudent.setAdmissionDate(Date.valueOf(request.getParameter("updatedAdmissionDate")));
			updatedStudent.setStatus(statusDao.findStatusById(Long.valueOf(request.getParameter("updatedStatusName"))));
			updatedStudent.setTerm(termDao.findTermById(Long.valueOf(request.getParameter("updatedTermName"))));
			studentDao.updateStudent(updatedStudent);
			response.sendRedirect("students?message=Updated student with id " + updatedStudent.getStudentId());
		}

	}
}
