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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("groups", studentGroupDao.getAll());
		request.setAttribute("statuses", statusDao.getAll());
		request.setAttribute("terms", termDao.getAll());
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/student/AddNewStudent.jsp");
		rs.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String bday = request.getParameter("bday");
        String eday = request.getParameter("eday");
        String group = request.getParameter("group");
        String term = request.getParameter("term");
        String status = request.getParameter("status");

		studentDao.create(firstName, lastName, Date.valueOf(bday), Date.valueOf(eday),
                studentGroupDao.getByStudentGroupName(group).getId(),
                termDao.getByTermAlias(term).getId(), statusDao.getByStatusName(status).getId());
		response.sendRedirect("Students.do");
	}

}
