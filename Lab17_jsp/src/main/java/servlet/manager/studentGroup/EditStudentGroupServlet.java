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
import database.entity.StudentGroup;
import database.dao.impl.H2DaoFactory;

@WebServlet("/editStudentGroup.do")
public class EditStudentGroupServlet extends HttpServlet {
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String tempId = request.getParameter("studentGroupId");		
		int id = Integer.parseInt(tempId);
		request.setAttribute("studentGroup", studentGroupDao.getByStudentGroupId(id));
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/studentGroup/EditStudentGroup.jsp");
		rs.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempId = request.getParameter("studentGroupId");
		int id = Integer.parseInt(tempId);
		StudentGroup group = studentGroupDao.getByStudentGroupId(id);
		group.setStudentGroupName(request.getParameter("studentGroupName"));
		studentGroupDao.update(group);
		response.sendRedirect("StudentGroups.do");				
	}
}
