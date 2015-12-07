package com.nixsolutions.servlet.manager.subject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.dao.TermDao;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.entity.Subject;

@WebServlet("/editSubject.do")
public class EditSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDao subjectDao;
	private TermDao termDao;

	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
			termDao = daoFactory.getTermDao();
			subjectDao = daoFactory.getSubjectDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String tempId = request.getParameter("subjectId");
		int subjectId = Integer.parseInt(tempId);
		Subject subject = subjectDao.getBySubjectId(subjectId);
		request.setAttribute("subject", subject);
		request.setAttribute("terms", termDao.getAll());
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/subject/EditSubject.jsp");
		try {
			rs.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempId = request.getParameter("subjectId");
		int subjectId = Integer.parseInt(tempId);		
		Subject subject = subjectDao.getBySubjectId(subjectId);		
		subject.setName(request.getParameter("subject"));
		subject.setTerm(termDao.getByTermAlias(request.getParameter("term")));
		subjectDao.update(subject);
		response.sendRedirect("Subjects.do");				
	}
}
