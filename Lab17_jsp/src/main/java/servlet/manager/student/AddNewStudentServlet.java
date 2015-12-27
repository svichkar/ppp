package servlet.manager.student;

import database.dao.*;
import database.dao.impl.H2DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/addNewStudent.do")
public class AddNewStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TermDao termDao;
	private StudentDao studentDao;
	private StudentGroupDao studentGroupDao;
	private StatusDao statusDao;

	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
			termDao = daoFactory.getTermDao();
			studentDao = daoFactory.getStudentDao();
			studentGroupDao = daoFactory.getStudentGroupDao();
			statusDao = daoFactory.getStatusDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("groups", studentGroupDao.getAll());
		request.setAttribute("statuses", statusDao.getAll());
		request.setAttribute("terms", termDao.getAll());
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/student/AddNewStudent.jsp");
		rs.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		studentDao.create(request.getParameter("firstName"), request.getParameter("lastName"),
				Date.valueOf(request.getParameter("bday")), Date.valueOf(request.getParameter("eday")),
				studentGroupDao.getByStudentGroupName(request.getParameter("group")).getId(),
				termDao.getByTermAlias(request.getParameter("term")).getId(),
				statusDao.getByStatusName(request.getParameter("status")).getId());
		response.sendRedirect("Students.do");
	}

}
