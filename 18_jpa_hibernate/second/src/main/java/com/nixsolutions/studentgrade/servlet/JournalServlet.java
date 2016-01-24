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
                Student student = j.getStudent();
                StudentGroup group = student.getStudentGroup();
                Subject subject = j.getSubject();
                Grade grade = j.getGrade();

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
                        if (student.getStudentId().equals(j.getStudent().getStudentId())
                                && subject.getSubjectId().equals(j.getSubject().getSubjectId()))
                            isUnique = false;
                    }

                    Journal journal = new Journal();
                    if (isUnique) {
                        if (student.getStudentGroup().getGroupId().equals(studentGroup.getGroupId())) {
                            journal.setGrade(grade);
                            journal.setSubject(subject);
                            journal.setStudent(student);
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
                    if (student.getStudentId().equals(j.getStudent().getStudentId())
                            && grade.getGradeId().equals(j.getGrade().getGradeId())
                            && subject.getSubjectId().equals(j.getSubject().getSubjectId()))
                        isUnique = false;
                }

                Journal journal = dao.findById(Long.valueOf(id));
                if (isUnique) {
                    journal.setStudent(student);
                    journal.setSubject(subject);
                    journal.setGrade(grade);
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
                Journal journal = dao.findById(Long.valueOf(id));
                dao.delete(journal);
                request.setAttribute("error", "<p><h4 style=\"font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;\">" +
                        "Success</h4></p>");
                break;
            }
        }

        List<JournalBean> list = new ArrayList<>();
        List<Journal> journalList = dao.findAll();
        if (journalList != null && journalList.isEmpty() == false) {
            for (Journal j : journalList) {
                Student st = j.getStudent();
                StudentGroup gr = st.getStudentGroup();
                Subject sub = j.getSubject();
                Grade g = j.getGrade();

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
