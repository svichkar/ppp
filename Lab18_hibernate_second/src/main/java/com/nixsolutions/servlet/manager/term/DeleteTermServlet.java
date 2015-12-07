package com.nixsolutions.servlet.manager.term;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.TermDao;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.entity.Term;

@WebServlet("/deleteTerm.do")
public class DeleteTermServlet extends HttpServlet {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempId = request.getParameter("termId");
		int termId = Integer.parseInt(tempId);
		Term term = termDao.getByTermId(termId);
		termDao.delete(term);
		response.sendRedirect("Terms.do");
	}

}
