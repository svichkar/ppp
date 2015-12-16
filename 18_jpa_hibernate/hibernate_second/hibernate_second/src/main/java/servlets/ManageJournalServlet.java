package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Journal;
import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.Subject;

public class ManageJournalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ManageJournalServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Student> studentList = DaoFactory.getStudent().getAll();
	List<Subject> subjectList = DaoFactory.getSubject().getAll();
	List<Journal> journalList = DaoFactory.getJournal().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("students", studentList);
	request.setAttribute("subjects", subjectList);
	request.setAttribute("journal", journalList);
	request.getRequestDispatcher("/WEB-INF/jspPages/journal/viewJournal.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

}
