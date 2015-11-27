package servlets;

import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ReaderDao;
import dao.impl.DBDaoFactory;
import entity.Reader;

/**
 * Servlet implementation class AddNewReader
 */
@WebServlet(name = "addNewReader", urlPatterns = "/addNewReader")
public class AddNewReader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();
		String readerName = request.getParameter("readerName");
		String readerAdress = request.getParameter("readerAdress");
		ReaderDao rf = factory.getReaderDao();
		rf.addReader(new Reader(readerName, readerAdress));
		List<Reader> readersList = rf.getAllReaders();
		request.setAttribute("readers", readersList);

		getServletContext().getRequestDispatcher("/WEB-INF/jsp/readersList.jsp")
				.forward(request, response);

	}

}
