package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.StudentGroup;

public class CreateStudentGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateStudentGroupServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<StudentGroup> groupList = DaoFactory.getStudentGroup().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("studentGroups", groupList);
	request.setAttribute("createOrEdit", "create");
	request.getRequestDispatcher("/WEB-INF/jspPages/studentGroups/createEditStudentGroups.jsp").include(request,
		response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	StudentGroup sg = new StudentGroup(0, request.getParameter("groupName"));
	Boolean isStudentGroupCreated = DaoFactory.getStudentGroup().create(sg);
	List<StudentGroup> groupList = DaoFactory.getStudentGroup().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("studentGroups", groupList);
	if (isStudentGroupCreated) {
	    request.getRequestDispatcher("/WEB-INF/jspPages/studentGroups/manageStudentGroups.jsp").include(request,
		    response);
	} else {
	    request.setAttribute("message", "Error has occured. Student group was not created.");
	    request.getRequestDispatcher("/WEB-INF/jspPages/studentGroups/createEditStudentGroups.jsp").include(request,
		    response);
	}
    }

}
