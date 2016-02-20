package com.nixsolutions.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Subject;

@WebServlet("/updateSubject")
public class UpdateSubjectPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static SubjectDAO subjectDao;
	private static TermDAO termDao;

	@Override
	public void init() throws ServletException {
		subjectDao = DAOFactory.getSubject();
		termDao = DAOFactory.getTerm();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("subject_id") != null) {
			Long subjectId = Long.valueOf(request.getParameter("subject_id"));
			Subject updateSubject = subjectDao.findSubjectById(subjectId);
			request.setAttribute("updateSubject", updateSubject);
			request.setAttribute("terms", termDao.findAllTerms());
			request.getRequestDispatcher("/WEB-INF/jsp/updateSubject.jsp").forward(request, response);
		} else
			response.sendRedirect("subjects?message=Please select subject for update");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			Long subjectId = Long.valueOf(request.getParameter("subject_id"));
			Subject updatedSubject = new Subject(subjectId, request.getParameter("updatedSubjectName"),
					Long.valueOf(request.getParameter("updatedTermName")));
			subjectDao.updateSubject(updatedSubject);
			response.sendRedirect("subjects?message=Updated subject with id " + subjectId);
		}
	}
}
