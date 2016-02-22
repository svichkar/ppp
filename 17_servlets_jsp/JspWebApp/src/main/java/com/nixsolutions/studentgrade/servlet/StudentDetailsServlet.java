package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.bean.StudentDetailsBean;
import com.nixsolutions.studentgrade.dao.*;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.entity.Term;
import com.nixsolutions.studentgrade.servlet.message.Message;
import com.nixsolutions.studentgrade.servlet.message.MessageType;

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

        Message m = new Message();
        switch (operation) {
            case "details": {
                getServletContext().log(request.getParameter("id"));
                request.setAttribute("id", request.getParameter("id"));
                m.setMessageType(MessageType.ERROR);
                m.setMessageText("Please specify term for student grade details");
                request.setAttribute("message", m);
                break;
            }

            case "show": {

                getServletContext().log(request.getParameter("id"));
                Long studentId = Long.valueOf(request.getParameter("id"));

                String termName = request.getParameter("term");
                Term term = termDao.findByName(termName);
                List<Journal> journalList = journalDao.findByStudentAndTerm(studentId, term.getTermId());
                if (journalList != null && journalList.isEmpty() == false) {

                    getServletContext().log("journalList is not empty");
                    request.setAttribute("selectedTerm", term);

                    Long sum = 0L;
                    for (Journal j : journalList) {
                        list.add(new StudentDetailsBean(subjectDao.findById(j.getSubjectId()).getSubjectName(),
                                gradeDao.findById(j.getGradeId()).getGradeName()));

                        sum += j.getGradeId();
                    }

                    Float score = Float.valueOf(sum) / journalList.size();
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);

                    getServletContext().log("average = " + df.format(score));
                    request.setAttribute("score", df.format(score));
                }

                if (list != null && list.isEmpty() == false) {
                    request.setAttribute("journals", list);
                    getServletContext().log("journal is not empty");
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("No data available. Please change search criteria");
                    request.setAttribute("message", m);
                }
                request.setAttribute("id", studentId);
                break;
            }
        }

        request.setAttribute("terms", termDao.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/student-journal.jsp").forward(request, response);
    }
}
