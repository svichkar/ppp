package com.nixsolutions.servlet.manager.term;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.TermDao;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.entity.Term;

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
    public AddNewTermServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/term/AddNewTerm.jsp");
		rs.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Term term = new Term();		
		term.setAlias(request.getParameter("alias"));		
		termDao.create(term);
		response.sendRedirect("Terms.do");
	}

}
