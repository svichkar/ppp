package servlet.manager.studentGroup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DaoFactory;
import database.dao.StudentGroupDao;
import database.dao.impl.H2DaoFactory;

@WebServlet("/addNewStudentGroup.do")
public class AddNewStudentGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentGroupDao studentGroupDao;

	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
			studentGroupDao = daoFactory.getStudentGroupDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/studentGroup/AddNewStudentGroup.jsp");
		rs.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		studentGroupDao.create(request.getParameter("studentGroupName"));
		response.sendRedirect("StudentGroups.do");
	}
}