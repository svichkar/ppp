package com.nixsolutions.servlet.manager.journal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.*;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.entity.Journal;

import java.io.IOException;
import java.util.List;

@WebServlet("/Journal.do")
public class JournalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private JournalDao journalDao;
    private StudentDao studentDao;
    
	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
            journalDao = daoFactory.getJournalDao();
            studentDao = daoFactory.getStudentDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Journal> journal = journalDao.getAll();		
		request.setAttribute("journal", journal);
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/journal/Journal.jsp");
		rs.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempId = request.getParameter("studentId");
        int id = Integer.parseInt(tempId);
        List<Journal> journal = journalDao.getJournalByStudentId(studentDao.getByStudentId(id).getStudentId());        
        request.setAttribute("journal", journal);
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/journal/Journal.jsp");
		rs.forward(request, response);
	}

}
