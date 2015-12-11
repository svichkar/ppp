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

public class CreateStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateStudentServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Student> studentList = DaoFactory.getStudent().getAll();
	List<Status> statusList = DaoFactory.getStatus().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	List<StudentGroup> groupList = DaoFactory.getStudentGroup().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("students", studentList);
	request.setAttribute("statuses", statusList);
	request.setAttribute("groups", groupList);
	request.setAttribute("terms", termList);
	request.setAttribute("createOrEdit", "create");
	request.getRequestDispatcher("/WEB-INF/jspPages/students/createEditStudents.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Student s = new Student();
	s.setFirstName(request.getParameter("firstName"));
	s.setLastName(request.getParameter("lastName"));
	s.setDateBirthday(Date.valueOf(request.getParameter("dateBirthday")));
	s.setDateEntry(Date.valueOf(request.getParameter("dateEntry")));
	s.setStudentGroup(DaoFactory.getStudentGroup().getStudentGroupByName(request.getParameter("group")));
	s.setStatus(DaoFactory.getStatus().getByStatusName(request.getParameter("status")));
	s.setTerm(DaoFactory.getTerm().getTermByAlias(request.getParameter("term")));
	Boolean isStudentCreated = DaoFactory.getStudent().create(s);
	List<Student> studentList = DaoFactory.getStudent().getAll();
	List<Status> statusList = DaoFactory.getStatus().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	List<StudentGroup> groupList = DaoFactory.getStudentGroup().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("students", studentList);
	request.setAttribute("statuses", statusList);
	request.setAttribute("groups", groupList);
	request.setAttribute("terms", termList);
	if (isStudentCreated) {
	    request.getRequestDispatcher("/WEB-INF/jspPages/students/manageStudents.jsp").include(request, response);
	} else {
	    request.setAttribute("message", "Error has occured. Student was not created.");
	    request.getRequestDispatcher("/WEB-INF/jspPages/students/createEditStudents.jsp").include(request,
		    response);
	}
    }

}
