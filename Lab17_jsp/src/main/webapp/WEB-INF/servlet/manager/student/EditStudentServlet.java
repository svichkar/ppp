package servlet.manager.student;

import bean.StudentBean;
import database.dao.*;
import database.entity.Student;
import database.dao.impl.H2DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/editStudent.do")
public class EditStudentServlet extends HttpServlet {
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String tempId = request.getParameter("studentId");
		int studentId = Integer.parseInt(tempId);
		Student studentTemp = studentDao.getByStudentId(studentId);
		request.setAttribute("student", new StudentBean(studentId, studentTemp.getFirstName(), studentTemp.getLastName(),
                studentTemp.getDateBirthday(), studentTemp.getDateEntry(),
                studentGroupDao.getByStudentGroupId(studentTemp.getStudentGroupId()).getStudentGroupName(),
                termDao.getByTermId(studentTemp.getTermId()).getAlias(),
                statusDao.getByStatusId(studentTemp.getStatusId()).getStatusName()));
		request.setAttribute("terms", termDao.getAll());
        request.setAttribute("groups", studentGroupDao.getAll());
        request.setAttribute("statuses", statusDao.getAll());
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/student/EditStudent.jsp");
		try {
			rs.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempId = request.getParameter("studentId");
		int studentId = Integer.parseInt(tempId);
		Student student = studentDao.getByStudentId(studentId);
		student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setDateBirthday(Date.valueOf(request.getParameter("bday")));
        student.setDateEntry(Date.valueOf(request.getParameter("eday")));
        student.setStudentGroupId(studentGroupDao.getByStudentGroupName(request.getParameter("group")).getId());
        student.setTermId(termDao.getByTermAlias(request.getParameter("term")).getId());
        student.setStatusId(statusDao.getByStatusName(request.getParameter("status")).getId());
		studentDao.update(student);
		response.sendRedirect("Students.do");				
	}
}
