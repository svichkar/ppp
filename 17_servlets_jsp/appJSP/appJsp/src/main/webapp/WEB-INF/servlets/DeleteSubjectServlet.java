package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Subject;
import com.nixsolutions.entity.Term;

public class DeleteSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteSubjectServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Subject subject = DaoFactory.getSubject().getSubjectById(Integer.parseInt(request.getParameter("delete")));
	if (!DaoFactory.getSubject().delete(subject)) {
	    request.setAttribute("message", "Error has occured. Subject was not deleted.");
	}
	List<Subject> subjectList = DaoFactory.getSubject().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	request.setAttribute("terms", termList);
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("subjects", subjectList);
	request.getRequestDispatcher("/WEB-INF/jspPages/subjects/manageSubjects.jsp").include(request, response);
    }

}
