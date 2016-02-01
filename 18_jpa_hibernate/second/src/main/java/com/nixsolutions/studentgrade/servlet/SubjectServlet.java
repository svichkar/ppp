package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.bean.SubjectBean;
import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.exception.CustomDaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "subject", urlPatterns = {"/subject"})
public class SubjectServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactory daoFactory = new DaoFactory();
        TermDao termDao = daoFactory.getTermDao();
        SubjectDao subjectDao = daoFactory.getSubjectDao();

        List<SubjectBean> list = new ArrayList<>();

        if (request.getParameter("operation") != null && request.getParameter("operation").equals("search")) {

            String subjectName = request.getParameter("subjectName");
            String selectedTerm = request.getParameter("selectedTerm");

            if (selectedTerm != null && selectedTerm.isEmpty() == false) {

                Term term = termDao.findByName(selectedTerm);

                if (subjectName != null && subjectName.isEmpty() == false) {
                    Subject res = subjectDao.findByNameAndTermId(subjectName, term.getTermId());
                    if (res != null && !res.isEmpty()) {
                        list.add(new SubjectBean(res.getSubjectId(),
                                res.getSubjectName(),
                                res.getTerm().getTermId(),
                                res.getTerm().getTermName()));
                        request.setAttribute("message", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:bold;text-align:center;color: black;\">" +
                                "Search results.<a href=\"subject\">Back to Subject List</a></h4></p>");
                    }
                } else {

                    List<Subject> search = subjectDao.findByTermId(term.getTermId());
                    if (search != null && search.isEmpty() == false) {
                        for (Subject s : search) {
                            list.add(new SubjectBean(s.getSubjectId(),
                                    s.getSubjectName(),
                                    s.getTerm().getTermId(),
                                    s.getTerm().getTermName()));
                        }
                        request.setAttribute("message", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:bold;text-align:center;color: black;\">" +
                                "Search results. <a href=\"subject\">Back to Subject List</a></h4></p>");
                    }
                }

            } else {
                if (subjectName != null && subjectName.isEmpty() == false) {
                    Subject res = subjectDao.findByName(subjectName);
                    if (res != null && !res.isEmpty()) {
                        Long id = res.getTerm().getTermId();
                        list.add(new SubjectBean(res.getSubjectId(),
                                res.getSubjectName(),
                                res.getTerm().getTermId(),
                                termDao.findById(id).getTermName()));
                        request.setAttribute("message", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:bold;text-align:center;color: black;\">" +
                                "Search results. <a href=\"subject\">Back to Subject List</a></h4></p>");
                    }
                } else {
                    request.setAttribute("errorSearch", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "Please change search criteria</h4></p>");
                }
            }
        } else {
            List<Subject> subjects = subjectDao.findAll();
            if (subjects != null && subjects.isEmpty() == false) {
                for (Subject s : subjects) {
                    String term = termDao.findById(s.getTerm().getTermId()).getTermName();
                    list.add(new SubjectBean(s.getSubjectId(), s.getSubjectName(), s.getTerm().getTermId(), term));
                }
            }
        }

        if (list != null && list.isEmpty() == false) {
            request.setAttribute("subjects", list);
            request.setAttribute("terms", termDao.findAll());
        } else {
            request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                    "No data available. <a href=\"subject\">Back to Subject List</a></h4></p>");
        }

        request.setAttribute("subjects", list);
        request.setAttribute("terms", termDao.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/subject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactory daoFactory = new DaoFactory();
        SubjectDao dao = daoFactory.getSubjectDao();
        TermDao termDao = daoFactory.getTermDao();

        String subjectId = request.getParameter("subjectId");
        String subjectName = request.getParameter("subjectName");
        String termName = request.getParameter("selectedTerm");
        String operation = request.getParameter("operation");

        switch (operation) {

            case "add": {
                boolean isUnique = true;
                for (Subject s : dao.findAll()) {
                    if (subjectName.equals(s.getSubjectName()))
                        isUnique = false;
                }

                if (isUnique) {
                    Subject subject = new Subject();
                    subject.setTerm(termDao.findByName(termName));
                    subject.setSubjectName(subjectName);
                    dao.create(subject);
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h4></p>");

                } else {
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "Subject already exists</h4></p>");
                }
                break;
            }

            case "update": {
                Subject subject = dao.findById(Long.valueOf(subjectId));
                subject.setSubjectName(subjectName);
                subject.setTerm(termDao.findByName(termName));

                boolean isUnique = true;
                for (Subject s : dao.findAll()) {
                    if (subject.getSubjectId() != s.getSubjectId() && subjectName.equals(s.getSubjectName()))
                        isUnique = false;
                }

                if (isUnique) {
                    dao.update(subject);
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h4></p>");

                } else {
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "Subject already exists</h4></p>");
                }
                break;
            }

            case "delete": {
                Subject subject = dao.findById(Long.valueOf(subjectId));
                try {
                    dao.delete(subject);
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h4></p>");
                } catch (CustomDaoException e) {
                    getServletContext().log(e.toString());
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "Subject can't be deleted</h4></p>");
                }
                break;
            }
        }

        List<SubjectBean> list = new ArrayList<>();
        for (Subject s : dao.findAll()) {
            list.add(new SubjectBean(s.getSubjectId(),
                    s.getSubjectName(),
                    s.getTerm().getTermId(),
                    s.getTerm().getTermName()));
        }
        request.setAttribute("subjects", list);
        request.setAttribute("terms", termDao.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/subject.jsp").forward(request, response);
    }

}
