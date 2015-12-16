package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.StudentGroup;

public class DeleteStudentGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteStudentGroupServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	StudentGroup studentGroup = DaoFactory.getStudentGroup()
		.getStudentGroupById(Integer.parseInt(request.getParameter("delete")));
	if (!DaoFactory.getStudentGroup().delete(studentGroup)) {
	    request.setAttribute("message", "Error has occured. Student group was not deleted.");
	}
	List<StudentGroup> groupList = DaoFactory.getStudentGroup().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("studentGroups", groupList);
	request.getRequestDispatcher("/WEB-INF/jspPages/studentGroups/manageStudentGroups.jsp").include(request,
		response);
    }

}
