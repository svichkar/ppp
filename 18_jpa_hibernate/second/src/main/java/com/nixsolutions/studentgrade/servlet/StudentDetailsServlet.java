package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.bean.StudentDetailsBean;
import com.nixsolutions.studentgrade.dao.*;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
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
        StudentDao studentDao = daoFactory.getStudentDao();

        List<StudentDetailsBean> list = new ArrayList<>();

        if (request.getParameter("id") != null) {
            Long studentId = Long.valueOf(request.getParameter("id"));
            Student student = studentDao.findById(studentId);
            request.setAttribute("pageTitle", String.format("<p><h3>Detailed grade statistic for <b>%s %s</b>. " +
                            "<a href=\"/student-grades/student\">Back to Students List</a></h3></p>",
                    student.getFirstName(), student.getLastName()));
        }

        switch (operation) {

            case "details": {

                request.setAttribute("id", request.getParameter("id"));
                request.setAttribute("message", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                        "Please specify term for student grade details</h4></p>");
                break;
            }

            case "show": {

                Long studentId = Long.valueOf(request.getParameter("id"));
                String termName = request.getParameter("term");
                Term term = termDao.findByName(termName);
                List<Journal> journalList = journalDao.findByStudentAndTerm(studentId, term.getTermId());

                if (journalList != null && journalList.isEmpty() == false) {

                    request.setAttribute("selectedTerm", term);

                    Long sum = 0L;
                    for (Journal j : journalList) {
                        list.add(new StudentDetailsBean(j.getSubject().getSubjectName(),
                                j.getGrade().getGradeName()));

                        sum += j.getGrade().getGradeId();
                    }

                    Float score = Float.valueOf(sum) / journalList.size();
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);

                    getServletContext().log("average = " + df.format(score));
                    request.setAttribute("score", df.format(score));
                }

                if (list != null && list.isEmpty() == false) {
                    request.setAttribute("journals", list);
                } else {
                    request.setAttribute("selectedTerm", term);
                    request.setAttribute("message", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;\">" +
                            "No data available. Please change search criteria</h4></p>");
                }
                request.setAttribute("id", studentId);
                break;
            }
        }

        request.setAttribute("terms", termDao.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/student-journal.jsp").forward(request, response);
    }
}
