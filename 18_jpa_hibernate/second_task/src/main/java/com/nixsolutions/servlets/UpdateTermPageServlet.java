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

@WebServlet("/updateTerm")
public class UpdateTermPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static TermDAO termDao;

	@Override
	public void init() throws ServletException {
		termDao = DAOFactory.getTerm();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = String.valueOf(request.getSession().getAttribute("role"));
		if ("manager".equals(role)) {
			if (request.getParameter("term_id") != null) {
				Long termId = Long.valueOf(request.getParameter("term_id"));
				Term updateTerm = termDao.findTermById(termId);
				request.setAttribute("updateTerm", updateTerm);
				request.getRequestDispatcher("/WEB-INF/jsp/updateTerm.jsp").forward(request, response);
			} else
				response.sendRedirect("terms?message=Please select term for update");
		} else {
			response.sendRedirect(
					"index.jsp?message=Your are not a manager. Please login as manager to continue work.");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			Long termId = Long.valueOf(request.getParameter("term_id"));
			Term updatedTerm = termDao.findTermById(termId);
			updatedTerm.setTermName(request.getParameter("updatedTermName"));
			termDao.updateTerm(updatedTerm);
			response.sendRedirect("terms?message=Updated term with id " + updatedTerm.getTermId());
		}
	}
}
