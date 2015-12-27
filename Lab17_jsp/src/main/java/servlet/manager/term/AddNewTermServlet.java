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
import database.dao.impl.H2DaoFactory;

@WebServlet("/addNewTerm.do")
public class AddNewTermServlet extends HttpServlet {
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
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/term/AddNewTerm.jsp");
		rs.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		termDao.create(request.getParameter("alias"));
		response.sendRedirect("Terms.do");
	}
}
