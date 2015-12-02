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

public class CreateSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateSubjectServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Subject> subjectList = DaoFactory.getSubject().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	request.setAttribute("terms", termList);
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("subjects", subjectList);
	request.setAttribute("createOrEdit", "create");
	request.getRequestDispatcher("/WEB-INF/jspPages/subjects/createEditSubjects.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Subject s = new Subject(0, request.getParameter("subjectName"),
		DaoFactory.getTerm().getTermByAlias(request.getParameter("term")).getTermId());
	Boolean isSubjectCreated = DaoFactory.getSubject().create(s);
	List<Subject> subjectList = DaoFactory.getSubject().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	request.setAttribute("terms", termList);
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("subjects", subjectList);
	if (isSubjectCreated) {
	    request.getRequestDispatcher("/WEB-INF/jspPages/subjects/manageSubjects.jsp").include(request, response);
	} else {
	    request.setAttribute("message", "Error has occured. Subject was not created.");
	    request.getRequestDispatcher("/WEB-INF/jspPages/subjects/createEditSubjects.jsp").include(request,
		    response);
	}
    }

}
