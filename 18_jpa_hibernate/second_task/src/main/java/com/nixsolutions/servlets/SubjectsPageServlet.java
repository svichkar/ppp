package com.nixsolutions.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Subject;

@WebServlet("/subjects")
public class SubjectsPageServlet extends HttpServlet {

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
		String role = String.valueOf(request.getSession().getAttribute("role"));
		if ("manager".equals(role)) {
			request.setAttribute("subjects", subjectDao.findAllSubjects());
			request.setAttribute("terms", termDao.findAllTerms());
			request.getRequestDispatcher("/WEB-INF/jsp/subjects.jsp").forward(request, response);
		} else {
			response.sendRedirect(
					"index.jsp?message=Your are not a manager. Please login as manager to continue work.");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("delete") != null) {
			Long subjectId = Long.valueOf(request.getParameter("subject_id"));
			subjectDao.deleteSubject(subjectDao.findSubjectById(subjectId));
			if (subjectDao.findSubjectById(subjectId) == null) {
				response.sendRedirect("subjects?message=Deleted subject with id " + subjectId);
			} else
				response.sendRedirect(
						"subjects?message=Subject is not deleted. Please delete associated journal records at first.");
		}
		if (request.getParameter("add") != null) {
			Subject newSubject = new Subject();
			newSubject.setSubjectName(request.getParameter("newSubjectName"));
			newSubject.setTerm(termDao.findTermById(Long.valueOf(request.getParameter("newTermName"))));
			subjectDao.createSubject(newSubject);
			response.sendRedirect("subjects?message=Added subject with id " + newSubject.getSubjectId());
		}

		if (request.getParameter("search") != null) {
			String subjectName = request.getParameter("searchSubjectName");
			String termId = request.getParameter("searchTermId");
			if (subjectName != null && termId != null) {
				Long termIdLong = Long.valueOf(termId);
				List<Subject> searchSubjects = subjectDao.findSubjectByNameAndTermId(subjectName, termIdLong);
				request.setAttribute("subjects", searchSubjects);
			}

			if (subjectName != null && termId == null) {
				List<Subject> searchSubjects = subjectDao.findSubjectsByName(subjectName);
				request.setAttribute("subjects", searchSubjects);
			}

			if (termId != null && subjectName.isEmpty()) {
				Long termIdLong = Long.valueOf(termId);
				List<Subject> searchSubjects = subjectDao.findSubjectsByTermId(termIdLong);
				request.setAttribute("subjects", searchSubjects);
			}
			request.setAttribute("terms", termDao.findAllTerms());
			request.getRequestDispatcher("/WEB-INF/jsp/subjects.jsp").forward(request, response);
		}
	}
}
