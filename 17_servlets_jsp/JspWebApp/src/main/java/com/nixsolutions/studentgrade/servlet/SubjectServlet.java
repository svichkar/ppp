package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.bean.SubjectBean;
import com.nixsolutions.studentgrade.dao.DaoFactory;
import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.servlet.message.Message;
import com.nixsolutions.studentgrade.servlet.message.MessageType;

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
        Message m = new Message();
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
                        m.setMessageType(MessageType.SUCCESS);
                        m.setMessageText("Search results. <a href=\"subject\">Back to Subject List</a>");
                        request.setAttribute("message", m);
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
                        m.setMessageType(MessageType.SUCCESS);
                        m.setMessageText("Search results. <a href=\"subject\">Back to Subject List</a>");
                        request.setAttribute("message", m);
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
                        m.setMessageType(MessageType.SUCCESS);
                        m.setMessageText("Search results. <a href=\"subject\">Back to Subject List</a>");
                        request.setAttribute("message", m);
                    }
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Please change search criteria");
                    request.setAttribute("message", m);
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
            m.setMessageType(MessageType.ERROR);
            m.setMessageText("No data available. <a href=\"subject\">Back to Subject List</a>");
            request.setAttribute("message", m);
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
        Message m = new Message();
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
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Subject already exists");
                    request.setAttribute("message", m);
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
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Subject already exists");
                    request.setAttribute("message", m);
                }
                break;
            }
            case "delete": {
                Subject subject = new Subject();
                subject.setSubjectId(Long.valueOf(subjectId));
                if (dao.delete(subject)) {
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Subject cannot be delete");
                    request.setAttribute("message", m);
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
