package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ReaderDao;
import dao.impl.DBDaoFactory;

@WebServlet(name = "getReaders", urlPatterns = "/getReaders")
public class GetReadersList extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();

		ReaderDao rf = factory.getReaderDao();
		request.setAttribute("readers", rf.getAllReaders());

		getServletContext().getRequestDispatcher("/WEB-INF/jsp/readersList.jsp")
				.forward(request, response);

	}

}
