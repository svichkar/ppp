package com.nixsolutions.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.studentgrade.bean.SubjectBean;
import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.entity.Term;

@WebServlet("/subjects")
public class SubjectsPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static SubjectDAO subjectDao;
	private static TermDAO termDao;
	private static Term term;

	@Override
	public void init() throws ServletException {
		subjectDao = DAOFactory.getSubject();
		termDao = DAOFactory.getTerm();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SubjectBean> displaySubjectList = new ArrayList<>();
		List<Subject> subjectList = subjectDao.findAllSubjects();
		for (Subject t : subjectList) {
			term = termDao.findTermById(t.getTermId());
			SubjectBean subject = new SubjectBean(t, term);
			displaySubjectList.add(subject);
		}
		request.setAttribute("subjects", displaySubjectList);
		request.setAttribute("terms", termDao.findAllTerms());
		request.getRequestDispatcher("/WEB-INF/jsp/subjects.jsp").forward(request, response);
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
			Long subjectId = Long.valueOf(subjectDao.findAllSubjects().size() + 1);
			Subject newSubject = new Subject(subjectId, request.getParameter("newSubjectName"),
					Long.valueOf(request.getParameter("newTermName")));
			subjectDao.createSubject(newSubject);
			response.sendRedirect("subjects?message=Added subject with id " + subjectId);
		}

		if (request.getParameter("search") != null) {
			String subjectName = request.getParameter("searchSubjectName");
			String termId = request.getParameter("searchTermId");
			if (subjectName != null && termId != null) {
				Long termIdLong = Long.valueOf(termId);
				List<Subject> searchSubjects = subjectDao.findSubjectByNameAndTermId(subjectName, termIdLong);
				List<SubjectBean> searchSubjectResult = this.displaySubjectList(searchSubjects);
				request.setAttribute("subjects", searchSubjectResult);
			}

			if (subjectName != null && termId == null) {
				List<Subject> searchSubjects = subjectDao.findSubjectsByName(subjectName);
				List<SubjectBean> searchSubjectResult = this.displaySubjectList(searchSubjects);
				request.setAttribute("subjects", searchSubjectResult);
			}

			if (termId != null && subjectName.isEmpty()) {
				Long termIdLong = Long.valueOf(termId);
				List<Subject> searchSubjects = subjectDao.findSubjectsByTermId(termIdLong);
				List<SubjectBean> searchSubjectResult = this.displaySubjectList(searchSubjects);
				request.setAttribute("subjects", searchSubjectResult);
			}
			request.setAttribute("terms", termDao.findAllTerms());
			request.getRequestDispatcher("/WEB-INF/jsp/subjects.jsp").forward(request, response);
		}
	}

	private List<SubjectBean> displaySubjectList(List<Subject> subjectList) {
		List<SubjectBean> displaySubjectList = new ArrayList<>();
		for (Subject t : subjectList) {
			term = termDao.findTermById(t.getTermId());
			SubjectBean subject = new SubjectBean(t, term);
			displaySubjectList.add(subject);
		}
		return displaySubjectList;
	}
}
