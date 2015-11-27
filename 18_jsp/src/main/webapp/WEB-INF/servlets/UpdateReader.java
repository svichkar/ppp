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

@WebServlet(name = "updateReader", urlPatterns = "/updateReader")
public class UpdateReader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBDaoFactory factory = new DBDaoFactory();
		ReaderDao rf = factory.getReaderDao();
		String[] checkBoxState = request.getParameterValues("checkboxstate");
		if (checkBoxState == null) {
			String readerName = request.getParameter("readerName");
			String readerAdress = request.getParameter("readerAdress");
			String id = request.getParameter("readerId");

			rf.updateReader(new Reader(new Integer(id).intValue(), readerName,
					readerAdress));

		} else {

			if (checkBoxState[0].toLowerCase().equals("on")) {
				String id = request.getParameter("readerId");
				if (!rf.delete("id=" + id)) {
					request.setAttribute("error",
							"<script>alert(\"This user has not yet returned all  books. Total debt is "
									+ rf.getCountOfDebtBooksForParticularUser(
											id)
									+ " book(s).\")</script>");
				}

			}
		}
		List<Reader> readersList = rf.getAllReaders();
		request.setAttribute("readers", readersList);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/readersList.jsp")
				.forward(request, response);
	}

}
