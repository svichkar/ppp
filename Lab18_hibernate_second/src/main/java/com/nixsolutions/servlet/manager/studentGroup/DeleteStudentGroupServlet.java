package com.nixsolutions.servlet.manager.studentGroup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.entity.StudentGroup;

@WebServlet("/deleteStudentGroup.do")
public class DeleteStudentGroupServlet extends HttpServlet {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tempId = request.getParameter("studentGroupId");
		int id = Integer.parseInt(tempId);
		StudentGroup group = studentGroupDao.getByStudentGroupId(id);
		studentGroupDao.delete(group);
		response.sendRedirect("StudentGroups.do");
	}

}
