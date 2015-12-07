package com.nixsolutions.servlet.manager.studentGroup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.entity.StudentGroup;

@WebServlet("/addNewStudentGroup.do")
public class AddNewStudentGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentGroupDao studentGroupDao;
    
	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
			studentGroupDao = daoFactory.getStudentGroupDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    public AddNewStudentGroupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/studentGroup/AddNewStudentGroup.jsp");
		rs.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentGroup studentGroup = new StudentGroup();
		studentGroup.setStudentGroupName(request.getParameter("studentGroupName"));
		studentGroupDao.create(studentGroup);
		response.sendRedirect("StudentGroups.do");
	}
}