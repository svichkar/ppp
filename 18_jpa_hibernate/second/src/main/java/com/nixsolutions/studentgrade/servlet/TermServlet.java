package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "term", urlPatterns = { "/term"})
public class TermServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactory daoFactory = new DaoFactory();
        List<Term> terms = daoFactory.getTermDao().findAll();
        request.setAttribute("terms", terms);
        request.getRequestDispatcher("/WEB-INF/jsp/term.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactory daoFactory = new DaoFactory();
        List<Term> terms = daoFactory.getTermDao().findAll();

        String termId = request.getParameter("termId");
        String termName = request.getParameter("termName");
        String operation = request.getParameter("operation");

        switch (operation) {

            case "add":{
                boolean isUnique = true;
                for (Term t : terms) {
                    if (termName.equals(t.getTermName()))
                        isUnique = false;
                }

                if(isUnique) {
                    daoFactory.getTermDao().create(new Term(termName));
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h4></p>");

                } else {
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "Specified Term already exists</h4></p>");

                }

                request.setAttribute("terms", daoFactory.getTermDao().findAll());
                request.getRequestDispatcher("/WEB-INF/jsp/term.jsp").forward(request, response);
                break;
            }

            case "update": {
                Term term = new Term();
                term.setTermId(Long.valueOf(termId));
                term.setTermName(termName);

                boolean isUnique = true;
                for (Term t : terms) {
                    if (termName.equals(t.getTermName()))
                        isUnique = false;
                }

                if(isUnique) {
                    daoFactory.getTermDao().update(term);
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h4></p>");

                } else {
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "Term already exists</h4></p>");

                }

                request.setAttribute("terms", daoFactory.getTermDao().findAll());
                request.getRequestDispatcher("/WEB-INF/jsp/term.jsp").forward(request, response);
                break;
            }

            case "delete" : {
                Term term = new Term();
                term.setTermId(Long.valueOf(termId));
                term.setTermName(termName);

                if (daoFactory.getTermDao().delete(term)) {
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h4></p>");

                } else {
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "Term cannot be delete</h4></p>");
                }

                request.setAttribute("terms", daoFactory.getTermDao().findAll());
                request.getRequestDispatcher("/WEB-INF/jsp/term.jsp").forward(request, response);
                break;
            }
        }
    }
}