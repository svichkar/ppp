package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.bean.SubjectBean;
import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.entity.Term;

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
                    if (!res.isEmpty()) {
                        Long id = res.getTermId();
                        list.add(new SubjectBean(res.getSubjectId(),
                                res.getSubjectName(),
                                res.getTermId(),
                                termDao.findById(id).getTermName()));
                        request.setAttribute("message", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:bold;text-align:center;color: black;\">" +
                                "Search results.<a href=\"subject\">Back to Subject List</a></h5></p>");
                    }
                } else {

                    List<Subject> search = subjectDao.findByTermId(term.getTermId());
                    if (search != null && search.isEmpty() == false) {
                        for (Subject s : search) {
                            list.add(new SubjectBean(s.getSubjectId(),
                                    s.getSubjectName(),
                                    s.getTermId(),
                                    termDao.findById(s.getTermId()).getTermName()));
                        }
                        request.setAttribute("message", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:bold;text-align:center;color: black;\">" +
                                "Search results. <a href=\"subject\">Back to Subject List</a></h5></p>");
                    }
                }

            } else {
                if (subjectName != null && subjectName.isEmpty() == false) {
                    Subject res = subjectDao.findByName(subjectName);
                    if (!res.isEmpty()) {
                        Long id = res.getTermId();
                        list.add(new SubjectBean(res.getSubjectId(),
                                res.getSubjectName(),
                                res.getTermId(),
                                termDao.findById(id).getTermName()));
                        request.setAttribute("message", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:bold;text-align:center;color: black;\">" +
                                "Search results. <a href=\"subject\">Back to Subject List</a></h5></p>");
                    }
                } else {
                    request.setAttribute("errorSearch", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                            "Please change search criteria</h5></p>");
                }
            }
        } else {
            List<Subject> subjects = subjectDao.findAll();
            if (subjects != null && subjects.isEmpty() == false) {
                for (Subject s : subjects) {
                    String term = termDao.findById(s.getTermId()).getTermName();
                    list.add(new SubjectBean(s.getSubjectId(), s.getSubjectName(), s.getTermId(), term));
                }
            }
        }

        if (list != null && list.isEmpty() == false) {
            request.setAttribute("subjects", list);
            request.setAttribute("terms", termDao.findAll());
        } else {
            request.setAttribute("error", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                    "No data available. <a href=\"subject\">Back to Subject List</a></h5></p>");
        }

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
                    Long termId = termDao.findByName(termName).getTermId();
                    dao.create(new Subject(subjectName, termId));
                    request.setAttribute("error", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h5></p>");

                } else {
                    request.setAttribute("error", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                            "Subject already exists</h5></p>");
                }
                break;
            }

            case "update": {
                Subject subject = new Subject();
                subject.setSubjectId(Long.valueOf(subjectId));
                subject.setSubjectName(subjectName);
                Term term = termDao.findByName(termName);
                subject.setTermId(term.getTermId());

                boolean isUnique = true;
                for (Subject s : dao.findAll()) {
                    if (subjectName.equals(s.getSubjectName()) && s.getTermId().equals(term.getTermId()))
                        isUnique = false;
                }

                if (isUnique) {
                    dao.update(subject);
                    request.setAttribute("error", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h5></p>");

                } else {
                    request.setAttribute("error", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                            "Subject already exists</h5></p>");
                }
                break;
            }

            case "delete": {
                Subject subject = new Subject();
                subject.setSubjectId(Long.valueOf(subjectId));

                if (dao.delete(subject)) {
                    request.setAttribute("error", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h5></p>");

                } else {
                    request.setAttribute("error", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                            "Subject cannot be delete</h5></p>");
                }
                break;
            }
        }

        List<SubjectBean> list = new ArrayList<>();
        for (Subject s : dao.findAll()) {
            String term = termDao.findById(s.getTermId()).getTermName();
            list.add(new SubjectBean(s.getSubjectId(), s.getSubjectName(), s.getTermId(), term));
        }
        request.setAttribute("subjects", list);
        request.setAttribute("terms", termDao.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/subject.jsp").forward(request, response);
    }
}
