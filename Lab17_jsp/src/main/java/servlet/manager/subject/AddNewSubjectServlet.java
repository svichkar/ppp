package servlet.manager.subject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DaoFactory;
import database.dao.SubjectDao;
import database.dao.TermDao;
import database.dao.impl.H2DaoFactory;

@WebServlet("/addNewSubject.do")
public class AddNewSubjectServlet extends HttpServlet {
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("terms", termDao.getAll());
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/subject/AddNewSubject.jsp");
		rs.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		subjectDao.create(request.getParameter("subject"), termDao.getByTermAlias(request.getParameter("term")).getId());
		response.sendRedirect("Subjects.do");
	}

}
