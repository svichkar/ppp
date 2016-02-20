package com.nixsolutions.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Term;

@WebServlet("/terms")
public class TermsPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static TermDAO termDao;

	@Override
	public void init() throws ServletException {
		termDao = DAOFactory.getTerm();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("terms", termDao.findAllTerms());
		request.getRequestDispatcher("/WEB-INF/jsp/terms.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("delete") != null) {
			Long termId = Long.valueOf(request.getParameter("term_id"));
			termDao.deleteTerm(termDao.findTermById(termId));
			if (termDao.findTermById(termId) == null) {
				response.sendRedirect("terms?message=Deleted term with id " + termId);
			} else
				response.sendRedirect(
						"terms?message=Term is not deleted. Please delete associated student and subject records at first.");
		}
		if (request.getParameter("add") != null) {
			Long termId = Long.valueOf(termDao.findAllTerms().size() + 1);
			Term newTerm = new Term(termId, request.getParameter("newTermName"));
			termDao.createTerm(newTerm);
			response.sendRedirect("terms?message=Added term with id " + termId);
		}
	}
}
