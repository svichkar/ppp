package com.nixsolutions.servlet.manager.student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.*;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.entity.Student;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/addNewStudent.do")
public class AddNewStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TermDao termDao;
	private StudentDao studentDao;
    private StudentGroupDao studentGroupDao;
    private StatusDao statusDao;
    
	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
			termDao = daoFactory.getTermDao();
			studentDao = daoFactory.getStudentDao();
            studentGroupDao = daoFactory.getStudentGroupDao();
            statusDao = daoFactory.getStatusDao();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("groups", studentGroupDao.getAll());
		request.setAttribute("statuses", statusDao.getAll());
		request.setAttribute("terms", termDao.getAll());
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/student/AddNewStudent.jsp");
		rs.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = new Student();
		student.setFirstName(request.getParameter("firstName"));
		student.setLastName(request.getParameter("lastName"));
		student.setDateBirthday(Date.valueOf(request.getParameter("bday")));
		student.setDateEntry(Date.valueOf( request.getParameter("eday")));
		student.setStudentGroup(studentGroupDao.getByStudentGroupName(request.getParameter("group")));
		student.setTerm(termDao.getByTermAlias(request.getParameter("term")));
		student.setStatus(statusDao.getByStatusName(request.getParameter("status")));		
		studentDao.create(student);
		response.sendRedirect("Students.do");
	}

}
