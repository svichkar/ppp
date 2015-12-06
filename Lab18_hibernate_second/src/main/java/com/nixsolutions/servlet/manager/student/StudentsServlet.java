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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Students.do")
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
    private StudentGroupDao studentGroupDao;
    
	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
            studentGroupDao = daoFactory.getStudentGroupDao();
			studentDao = daoFactory.getStudentDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        List<Student> students = studentDao.getAll();        
        request.setAttribute("students", students);
        request.setAttribute("groups", studentGroupDao.getAll());
        RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/student/Students.jsp");
        rs.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = new ArrayList<>();
        String firstName = request.getParameter("firstName");
        if(firstName != null){
            String lastName = request.getParameter("lastName");
            students.addAll(studentDao.getByStudentsByName(firstName, lastName));
        }else{
            String group = request.getParameter("studentGroup");
            students.addAll(studentDao.getStudentsByGroup(studentGroupDao.getByStudentGroupName(group)));
        }        
		request.setAttribute("students", students);
        request.setAttribute("groups", studentGroupDao.getAll());
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/student/Students.jsp");
		rs.forward(request, response);
	}

}
