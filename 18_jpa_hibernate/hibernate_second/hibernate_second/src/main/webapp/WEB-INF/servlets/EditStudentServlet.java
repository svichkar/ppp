package servlets;

import java.io.IOException;
import java.sql.Date;
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

public class EditStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditStudentServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Student> studentList = DaoFactory.getStudent().getAll();
	List<Status> statusList = DaoFactory.getStatus().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	List<StudentGroup> groupList = DaoFactory.getStudentGroup().getAll();
	Student student = DaoFactory.getStudent().getStudentById(Integer.parseInt(request.getParameter("editStudent")));
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("students", studentList);
	request.setAttribute("statuses", statusList);
	request.setAttribute("groups", groupList);
	request.setAttribute("terms", termList);
	request.setAttribute("studentToEdit", student);
	request.setAttribute("createOrEdit", "edit");
	request.getRequestDispatcher("/WEB-INF/jspPages/students/createEditStudents.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Student student = DaoFactory.getStudent().getStudentById(Integer.parseInt(request.getParameter("studentId")));
	student.setFirstName(request.getParameter("firstName"));
	student.setLastName(request.getParameter("lastName"));
	student.setDateBirthday(Date.valueOf(request.getParameter("dateBirthday")));
	student.setDateEntry(Date.valueOf(request.getParameter("dateEntry")));
	student.setStatus(DaoFactory.getStatus().getByStatusName(request.getParameter("status")));
	student.setTerm(DaoFactory.getTerm().getTermByAlias(request.getParameter("term")));
	student.setStudentGroup(DaoFactory.getStudentGroup().getStudentGroupByName(request.getParameter("group")));
	Boolean isUpdated = DaoFactory.getStudent().update(student);
	List<Student> studentList = DaoFactory.getStudent().getAll();
	List<Status> statusList = DaoFactory.getStatus().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	List<StudentGroup> groupList = DaoFactory.getStudentGroup().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("students", studentList);
	request.setAttribute("statuses", statusList);
	request.setAttribute("groups", groupList);
	request.setAttribute("terms", termList);
	if (isUpdated) {
	    request.getRequestDispatcher("/WEB-INF/jspPages/students/manageStudents.jsp").include(request, response);
	} else {
	    request.setAttribute("createOrEdit", "edit");
	    request.setAttribute("studentToEdit", student);
	    request.setAttribute("message", "Error has occured. Student was not created.");
	    request.getRequestDispatcher("/WEB-INF/jspPages/students/createEditStudents.jsp").include(request,
		    response);
	}
    }

}
