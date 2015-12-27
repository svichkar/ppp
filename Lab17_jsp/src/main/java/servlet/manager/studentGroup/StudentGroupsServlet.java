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

@WebServlet("/StudentGroups.do")
public class StudentGroupsServlet extends HttpServlet {
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
		request.setAttribute("studentGroups", studentGroupDao.getAll());
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/studentGroup/StudentGroups.jsp");
		rs.include(request, response);
	}
}
