package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.servlet.message.Message;
import com.nixsolutions.studentgrade.servlet.message.MessageType;

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
        Message m = new Message();
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
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Specified Term already exists");
                    request.setAttribute("message", m);
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
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Term already exists");
                    request.setAttribute("message", m);
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
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Term cannot be delete");
                    request.setAttribute("message", m);
                }
                request.setAttribute("terms", daoFactory.getTermDao().findAll());
                request.getRequestDispatcher("/WEB-INF/jsp/term.jsp").forward(request, response);
                break;
            }
        }
    }
}