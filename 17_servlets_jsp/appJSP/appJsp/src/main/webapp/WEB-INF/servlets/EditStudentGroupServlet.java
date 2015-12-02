package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.StudentGroup;

public class EditStudentGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditStudentGroupServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<StudentGroup> groupList = DaoFactory.getStudentGroup().getAll();
	StudentGroup sg = DaoFactory.getStudentGroup()
		.getStudentGroupById(Integer.parseInt(request.getParameter("editStudentGroup")));
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("studentGroups", groupList);
	request.setAttribute("createOrEdit", "edit");
	request.setAttribute("groupToEdit", sg);
	request.getRequestDispatcher("/WEB-INF/jspPages/studentGroups/createEditStudentGroups.jsp").include(request,
		response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	StudentGroup studentGroup = DaoFactory.getStudentGroup()
		.getStudentGroupById(Integer.parseInt(request.getParameter("studentGroupId")));
	studentGroup.setGroupName(request.getParameter("groupName"));
	Boolean isUpdated = DaoFactory.getStudentGroup().update(studentGroup);
	List<StudentGroup> groupList = DaoFactory.getStudentGroup().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("studentGroups", groupList);
	if (isUpdated) {
	    request.getRequestDispatcher("/WEB-INF/jspPages/studentGroups/manageStudentGroups.jsp").include(request,
		    response);
	} else {
	    request.setAttribute("createOrEdit", "edit");
	    request.setAttribute("studentGroupToEdit", studentGroup);
	    request.setAttribute("message", "Error has occured. Student group was not created.");
	    request.getRequestDispatcher("/WEB-INF/jspPages/studentGroups/createEditStudentGroups.jsp").include(request,
		    response);
	}
    }

}
