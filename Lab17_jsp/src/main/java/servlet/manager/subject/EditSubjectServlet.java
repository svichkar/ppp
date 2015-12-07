package servlet.manager.subject;

import java.io.IOException;

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
		Subject subjectTemp = subjectDao.getBySubjectId(subjectId);
		String term = termDao.getByTermId(subjectDao.getBySubjectId(subjectId).getTermId()).getAlias();		
		request.setAttribute("subject", new SubjectBean(subjectId, subjectTemp.getName(), term));
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
		subject.setTermId(termDao.getByTermAlias(request.getParameter("term")).getId());
		subjectDao.update(subject);
		response.sendRedirect("Subjects.do");				
	}
}
