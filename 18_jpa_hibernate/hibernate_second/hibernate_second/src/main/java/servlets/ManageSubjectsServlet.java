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

public class ManageSubjectsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ManageSubjectsServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Subject> subjectList = DaoFactory.getSubject().getAll();
	List<Term> termList = DaoFactory.getTerm().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("subjects", subjectList);
	request.setAttribute("terms", termList);
	request.getRequestDispatcher("/WEB-INF/jspPages/subjects/manageSubjects.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

}
