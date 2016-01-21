package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.bean.JournalBean;
import com.nixsolutions.studentgrade.dao.*;
import com.nixsolutions.studentgrade.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "journal", urlPatterns = {"/journal"})
public class JournalServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactory daoFactory = new DaoFactory();
        JournalDao dao = daoFactory.getJournalDao();
        SubjectDao subjectDao = daoFactory.getSubjectDao();
        StudentDao studentDao = daoFactory.getStudentDao();
        StudentGroupDao groupDao = daoFactory.getStudentGroupDao();
        GradeDao gradeDao = daoFactory.getGradeDao();

        List<JournalBean> list = new ArrayList<>();
        List<Journal> journals = dao.findAll();
        if (journals != null && journals.isEmpty() == false) {
            for (Journal j : journals) {
                Student student = studentDao.findById(j.getStudentId());
                StudentGroup group = groupDao.findById(student.getGroupId());
                Subject subject = subjectDao.findById(j.getSubjectId());
                Grade grade = gradeDao.findById(j.getGradeId());

                list.add(new JournalBean(j.getJournalId(),
                        student.getFirstName(),
                        student.getLastName(),
                        group.getGroupId(),
                        group.getGroupName(),
                        subject.getSubjectId(),
                        subject.getSubjectName(),
                        grade.getGradeId(),
                        grade.getGradeName()
                ));
            }
        }

        if (list != null && list.isEmpty() == false) {
            request.setAttribute("journals", list);
            request.setAttribute("groups", groupDao.findAll());
            request.setAttribute("subjects", subjectDao.findAll());
            request.setAttribute("grades", gradeDao.findAll());
        } else {
            request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                    "No data available.</h4></p>");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/journal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactory daoFactory = new DaoFactory();
        JournalDao dao = daoFactory.getJournalDao();
        SubjectDao subjectDao = daoFactory.getSubjectDao();
        StudentDao studentDao = daoFactory.getStudentDao();
        StudentGroupDao groupDao = daoFactory.getStudentGroupDao();
        GradeDao gradeDao = daoFactory.getGradeDao();

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String group = request.getParameter("group");
        String subjectName = request.getParameter("subject");
        String gradeName = request.getParameter("grade");
        String operation = request.getParameter("operation");

        Student student = studentDao.findByNameAndLastName(name, lastName);
        Subject subject = subjectDao.findByName(subjectName);
        StudentGroup studentGroup = groupDao.findByName(group);
        Grade grade = gradeDao.findByName(gradeName);

        switch (operation) {

            case "add": {

                if (student != null && student.isEmpty() == false) {
                    boolean isUnique = true;
                    for (Journal j : dao.findAll()) {
                        if (student.getStudentId().equals(j.getStudentId())
                                && subject.getSubjectId().equals(j.getSubjectId()))
                            isUnique = false;
                    }

                    Journal journal = new Journal();
                    if (isUnique) {
                        if(student.getGroupId().equals(studentGroup.getGroupId())) {
                            journal.setGradeId(grade.getGradeId());
                            journal.setSubjectId(subject.getSubjectId());
                            journal.setStudentId(student.getStudentId());
                            dao.create(journal);
                            request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                                    "Success</h4></p>");
                        } else {
                            request.setAttribute("error", String.format("<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                                    "Wrong group '<b>%s</b>' for entered student. Please check <a href=\"student\">Students List</a></h4></p>", group));
                        }

                    } else {
                        request.setAttribute("error", String.format("<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                                "Journal record for subject '<b>%s</b>' already exists</h4></p>", subjectName));
                    }
                } else {
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                            "Student doesn't exist. Please check <a href=\"student\">Students List</a></h4></p>");
                }
                break;
            }

            case "update": {

                boolean isUnique = true;
                for (Journal j : dao.findAll()) {
                    if (student.getStudentId().equals(j.getStudentId())
                            && grade.getGradeId().equals(j.getGradeId())
                            && subject.getSubjectId().equals(j.getSubjectId()))
                        isUnique = false;
                }

                Journal journal = new Journal();
                if (isUnique) {
                    journal.setJournalId(Long.valueOf(id));
                    journal.setStudentId(student.getStudentId());
                    journal.setSubjectId(subject.getSubjectId());
                    journal.setGradeId(grade.getGradeId());
                    dao.update(journal);
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h4></p>");
                } else {
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                            "Journal record already exists</h4></p>");
                }
                break;
            }

            case "delete": {
                Journal delete = new Journal();
                delete.setJournalId(Long.valueOf(id));

                if (dao.delete(delete)) {
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                            "Success</h4></p>");

                } else {
                    request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: red;\">" +
                            "Journal record cannot be delete</h4></p>");
                }
                break;
            }
        }

        List<JournalBean> list = new ArrayList<>();
        List<Journal> journalList = dao.findAll();
        if (journalList != null && journalList.isEmpty() == false) {
            for (Journal j : journalList) {
                Student st = studentDao.findById(j.getStudentId());
                StudentGroup gr = groupDao.findById(st.getGroupId());
                Subject sub = subjectDao.findById(j.getSubjectId());
                Grade g = gradeDao.findById(j.getGradeId());

                list.add(new JournalBean(j.getJournalId(),
                        st.getFirstName(),
                        st.getLastName(),
                        gr.getGroupId(),
                        gr.getGroupName(),
                        sub.getSubjectId(),
                        sub.getSubjectName(),
                        g.getGradeId(),
                        g.getGradeName()
                ));
            }
        }

        request.setAttribute("journals", list);
        request.setAttribute("groups", groupDao.findAll());
        request.setAttribute("subjects", subjectDao.findAll());
        request.setAttribute("grades", gradeDao.findAll());

        request.getRequestDispatcher("/WEB-INF/jsp/journal.jsp").forward(request, response);
    }
}
