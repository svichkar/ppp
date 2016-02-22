package com.nixsolutions.studentgrade.servlet;

import com.nixsolutions.studentgrade.bean.JournalBean;
import com.nixsolutions.studentgrade.dao.*;
import com.nixsolutions.studentgrade.entity.*;
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
            Message m = new Message();
            m.setMessageType(MessageType.ERROR);
            m.setMessageText("No data available");
            request.setAttribute("message", m);
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

        Message m = new Message();
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
                            m.setMessageType(MessageType.SUCCESS);
                            m.setMessageText("Success");
                            request.setAttribute("message", m);
                        } else {
                            m.setMessageType(MessageType.ERROR);
                            m.setMessageText(String.format("Wrong group '<b>%s</b>' for entered student. Please check <a href=\"student\">Students List</a>", group));
                            request.setAttribute("message", m);
                        }

                    } else {
                        m.setMessageType(MessageType.ERROR);
                        m.setMessageText(String.format("Journal record for subject '<b>%s</b>' already exists", subjectName));
                        request.setAttribute("message", m);
                    }
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Student doesn't exist. Please check <a href=\"student\">Students List</a>");
                    request.setAttribute("message", m);
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
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Journal record already exists");
                    request.setAttribute("message", m);
                }
                break;
            }

            case "delete": {
                Journal delete = new Journal();
                delete.setJournalId(Long.valueOf(id));

                if (dao.delete(delete)) {
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("\"Journal record cannot be delete");
                    request.setAttribute("message", m);
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
