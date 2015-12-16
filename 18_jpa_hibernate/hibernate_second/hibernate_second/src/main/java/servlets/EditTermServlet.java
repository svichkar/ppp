package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Term;

public class EditTermServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditTermServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Term> termList = DaoFactory.getTerm().getAll();
	Term t = DaoFactory.getTerm().getByTermId(Integer.parseInt(request.getParameter("editTerm")));
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("terms", termList);
	request.setAttribute("createOrEdit", "edit");
	request.setAttribute("termToEdit", t);
	request.getRequestDispatcher("/WEB-INF/jspPages/terms/createEditTerms.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Term term = DaoFactory.getTerm().getByTermId(Integer.parseInt(request.getParameter("termId")));
	term.setTermAlias(request.getParameter("termAlias"));
	Boolean isUpdated = DaoFactory.getTerm().update(term);
	List<Term> termList = DaoFactory.getTerm().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("terms", termList);
	if (isUpdated) {
	    request.getRequestDispatcher("/WEB-INF/jspPages/terms/manageTerms.jsp").include(request, response);
	} else {
	    request.setAttribute("createOrEdit", "edit");
	    request.setAttribute("termToEdit", term);
	    request.setAttribute("message", "Error has occured. Term was not created.");
	    request.getRequestDispatcher("/WEB-INF/jspPages/terms/createEditTerms.jsp").include(request, response);
	}
    }

}
