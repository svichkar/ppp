package com.nixsolutions.servlet.manager.subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.entity.Subject;

@WebServlet("/deleteSubject.do")
public class DeleteSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SubjectDao subjectDao;
    
	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
			subjectDao = daoFactory.getSubjectDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempId = request.getParameter("subjectId");
		int subjectId = Integer.parseInt(tempId);
		Subject subject = subjectDao.getBySubjectId(subjectId);
		subjectDao.delete(subject);
		response.sendRedirect("Subjects.do");
	}

}
