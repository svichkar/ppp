package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Status;
import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.StudentGroup;
import com.nixsolutions.entity.Term;

public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteStudentServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Student student = DaoFactory.getStudent().getStudentById(Integer.parseInt(request.getParameter("delete")));
	if (!DaoFactory.getStudent().delete(student)) {
	    request.setAttribute("message", "Error has occured. Student was not deleted.");
	}
	List<Student> studentList = DaoFactory.getStudent().getAll();
	List<Status> statusList = DaoFactory.getStatus().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	List<StudentGroup> groupList = DaoFactory.getStudentGroup().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("students", studentList);
	request.setAttribute("statuses", statusList);
	request.setAttribute("groups", groupList);
	request.setAttribute("terms", termList);
	request.getRequestDispatcher("/WEB-INF/jspPages/students/manageStudents.jsp").include(request, response);
    }

}
