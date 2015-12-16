package servlets;

import java.io.IOException;
import java.util.ArrayList;
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

public class SearchStudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchStudentsServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Student> studentList = new ArrayList<Student>();
	if (request.getParameter("radioSearch") == null) {
	    studentList = DaoFactory.getStudent().getAll();
	} else {
	    if (request.getParameter("radioSearch").equals("SearchByFirstAndLastName")) {
		studentList = DaoFactory.getStudent().getStudentsByName(request.getParameter("searchByFirstName"),
			request.getParameter("searchByLastName"));
	    } else {
		if (request.getParameter("radioSearch").equals("SearchByGroup")) {
		    studentList = DaoFactory.getStudent().getStudentsByGroup(DaoFactory.getStudentGroup()
			    .getStudentGroupByName(request.getParameter("searchByGroup")));
		}
	    }
	}
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

}
