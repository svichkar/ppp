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

public class EditSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditSubjectServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Subject> subjectList = DaoFactory.getSubject().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	Subject s = DaoFactory.getSubject().getSubjectById(Integer.parseInt(request.getParameter("editSubject")));
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("subjects", subjectList);
	request.setAttribute("terms", termList);
	request.setAttribute("createOrEdit", "edit");
	request.setAttribute("subjectToEdit", s);
	request.getRequestDispatcher("/WEB-INF/jspPages/subjects/createEditSubjects.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Subject subject = DaoFactory.getSubject().getSubjectById(Integer.parseInt(request.getParameter("subjectId")));
	subject.setSubjectName(request.getParameter("subjectName"));
	Boolean isUpdated = DaoFactory.getSubject().update(subject);
	List<Subject> subjectList = DaoFactory.getSubject().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	request.setAttribute("terms", termList);
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("subjects", subjectList);
	if (isUpdated) {
	    request.getRequestDispatcher("/WEB-INF/jspPages/subjects/manageSubjects.jsp").include(request, response);
	} else {
	    request.setAttribute("createOrEdit", "edit");
	    request.setAttribute("subjectToEdit", subject);
	    request.setAttribute("message", "Error has occured. Subject was not created.");
	    request.getRequestDispatcher("/WEB-INF/jspPages/subjects/createEditSubjects.jsp").include(request,
		    response);
	}
    }

}
