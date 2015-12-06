package com.nixsolutions.servlet.manager.student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.StudentDao;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.entity.Student;

import java.io.IOException;

@WebServlet("/deleteStudent.do")
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentDao studentDao;
    
	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
			studentDao = daoFactory.getStudentDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempId = request.getParameter("studentId");
		int studentId = Integer.parseInt(tempId);
		Student student = studentDao.getByStudentId(studentId);
		studentDao.delete(student);
		response.sendRedirect("Students.do");
	}

}
