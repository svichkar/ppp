package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Term;

public class CreateTermServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateTermServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Term> termList = DaoFactory.getTerm().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("terms", termList);
	request.setAttribute("createOrEdit", "create");
	request.getRequestDispatcher("/WEB-INF/jspPages/terms/createEditTerms.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Term t = new Term(0, request.getParameter("termAlias"));
	Boolean isTermCreated = DaoFactory.getTerm().create(t);
	List<Term> termList = DaoFactory.getTerm().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("terms", termList);
	if (isTermCreated) {
	    request.getRequestDispatcher("/WEB-INF/jspPages/terms/manageTerms.jsp").include(request, response);
	} else {
	    request.setAttribute("message", "Error has occured. Term was not created.");
	    request.getRequestDispatcher("/WEB-INF/jspPages/terms/createEditTerms.jsp").include(request, response);
	}
    }

}
