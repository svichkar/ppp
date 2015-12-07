package servlet.manager.subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SubjectBean;
import database.dao.DaoFactory;
import database.dao.SubjectDao;
import database.dao.TermDao;
import database.entity.Subject;
import database.dao.impl.H2DaoFactory;

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
		List<SubjectBean> subjects = new ArrayList<>();
		List<Subject> subjectsTemp = subjectDao.getAll();
		for (Subject subject : subjectsTemp){
			subjects.add(new SubjectBean(subject.getId(), subject.getName(), termDao.getByTermId(subject.getTermId()).getAlias()));
		}		
		request.setAttribute("subjects", subjects);
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/subject/Subjects.jsp");
		rs.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SubjectBean> subjects = new ArrayList<>();
		String searchType = request.getParameter("searchType");
		String query = request.getParameter("searchQuery");
		if (searchType.equals("subject")){
			List<Subject> subjectsTemp = subjectDao.getBySubjectName(query);
			for (Subject subject : subjectsTemp){
				subjects.add(new SubjectBean(subject.getId(), subject.getName(), termDao.getByTermId(subject.getTermId()).getAlias()));
			}	
		}else{
			List<Subject> subjectsTemp = subjectDao.getSubjectsByTermId(termDao.getByTermAlias(query).getId());
			for (Subject subject : subjectsTemp){
				subjects.add(new SubjectBean(subject.getId(), subject.getName(), termDao.getByTermId(subject.getTermId()).getAlias()));
			}
		}
		request.setAttribute("subjects", subjects);
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/subject/Subjects.jsp");
		rs.forward(request, response);
	}

}
