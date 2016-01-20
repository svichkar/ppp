package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.bean.StudentDetailsBean;
import com.nixsolutions.studentgrade.dao.*;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 1/18/2016.
 */
@WebServlet(name = "studentDetails", urlPatterns = {"/journal/student"})
public class StudentDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operation = request.getParameter("operation");

        DaoFactory daoFactory = new DaoFactory();
        TermDao termDao = daoFactory.getTermDao();
        JournalDao journalDao = daoFactory.getJournalDao();
        SubjectDao subjectDao = daoFactory.getSubjectDao();
        GradeDao gradeDao = daoFactory.getGradeDao();

        List<StudentDetailsBean> list = new ArrayList<>();

        switch (operation) {

            case "details": {
                request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                        "Please specify term for student grade details</br><a href=\"/student-grades/student\">Back to Students List</a></h4></p>");
                break;
            }

            case "show": {

                Long studentId = Long.valueOf(request.getParameter("id"));
                String termName = request.getParameter("term");
                Term term = termDao.findByName(termName);
                List<Journal> journalList = journalDao.findByStudentAndTerm(studentId, term.getTermId());
                if (journalList != null && journalList.isEmpty() == false) {

                    for (Journal j : journalList) {
                        list.add(new StudentDetailsBean(subjectDao.findById(j.getSubjectId()).getSubjectName(),
                                gradeDao.findById(j.getGradeId()).getGradeName()));
                    }
                }

                if (list != null && list.isEmpty() == false) {
                    request.setAttribute("journals", list);
                } else {
                    request.setAttribute("error", "<p><h5 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                            "No data available. Please change search criteria.</br><a href=\"student\">Back to Students List</a></h5></p>");
                }
                request.setAttribute("id", studentId);
                break;
            }
        }

        request.setAttribute("terms", termDao.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/student-journal.jsp").forward(request, response);
    }
}
