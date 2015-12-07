package com.nixsolutions.servlet.manager.subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/Subjects.do")
public class SubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TermDao termDao;
	private SubjectDao subjectDao;
    
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Subject> subjects = subjectDao.getAll();			
		request.setAttribute("subjects", subjects);
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/subject/Subjects.jsp");
		rs.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Subject> subjects = new ArrayList<>();
		String searchType = request.getParameter("searchType");
		String query = request.getParameter("searchQuery");
		if (searchType.equals("subject")){
			subjects = subjectDao.getBySubjectName(query);			
		}else{
			subjects = subjectDao.getSubjectsByTerm(termDao.getByTermAlias(query));			
		}
		request.setAttribute("subjects", subjects);
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/subject/Subjects.jsp");
		rs.forward(request, response);
	}

}
