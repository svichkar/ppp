package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JournalDao;
import dao.ReaderDao;
import dao.impl.DBDaoFactory;
import entity.Journal;
import entity.Reader;

@WebServlet(name = "journal", urlPatterns = "/journal")
public class JournalDoc extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();
		JournalDao journal = factory.getJournalDao();
		List<Journal> journalFullList = journal.getAllJournalRecords();
		journalFullList = journal.addInfoToJournal(journalFullList);

		request.setAttribute("journal", journalFullList);

		getServletContext().getRequestDispatcher("/WEB-INF/jsp/journalView.jsp")
				.forward(request, response);

	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
