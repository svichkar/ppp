package servlet.manager.term;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DaoFactory;
import database.dao.TermDao;
import database.entity.Term;
import database.dao.impl.H2DaoFactory;

@WebServlet("/editTerm.do")
public class EditTermServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TermDao termDao;

	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
			termDao = daoFactory.getTermDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int termId = Integer.parseInt(request.getParameter("termId"));
		request.setAttribute("term", termDao.getByTermId(termId));
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/term/EditTerm.jsp");
		try {
			rs.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		termDao.update(new Term(termDao.getByTermAlias(request.getParameter("aliasOld")).getId(),
				request.getParameter("alias")));
		response.sendRedirect("Terms.do");
	}
}
