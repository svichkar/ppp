package com.nixsolutions.studentgrade.servlet;


import com.nixsolutions.studentgrade.bean.StudentBean;
import com.nixsolutions.studentgrade.dao.*;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.servlet.message.Message;
import com.nixsolutions.studentgrade.servlet.message.MessageType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "student", urlPatterns = {"/student"})
public class StudentServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoFactory daoFactory = new DaoFactory();
        TermDao termDao = daoFactory.getTermDao();
        StudentGroupDao groupDao = daoFactory.getStudentGroupDao();
        StatusDao statusDao = daoFactory.getStatusDao();
        StudentDao dao = daoFactory.getStudentDao();
        List<StudentBean> list = new ArrayList<>();
        Message m = new Message();

        if (request.getParameter("operation") != null && request.getParameter("operation").equals("search")) {

            String lastName = request.getParameter("lastName");
            String group = request.getParameter("group");

            if (group != null && group.isEmpty() == false) {

                StudentGroup studentGroup = groupDao.findByName(group);

                if (lastName != null && lastName.isEmpty() == false) {
                    List<Student> resList = dao.findByLastNameAndGroupId(lastName, studentGroup.getGroupId());
                    if (resList != null && !resList.isEmpty()) {
                        for (Student st : resList) {

                            list.add(new StudentBean(st.getStudentId(),
                                    st.getFirstName(),
                                    st.getLastName(),
                                    st.getAdmissionDate(),
                                    st.getGroupId(),
                                    studentGroup.getGroupName(),
                                    st.getStatusId(),
                                    statusDao.findById(st.getStatusId()).getStatusName(),
                                    st.getTermId(),
                                    termDao.findById(st.getTermId()).getTermName()
                            ));
                        }
                        m.setMessageType(MessageType.SUCCESS);
                        m.setMessageText("Search results. <a href=\"student\">Back to Student List</a>");
                        request.setAttribute("message", m);
                    }
                } else {

                    List<Student> search = dao.findByGroupId(studentGroup.getGroupId());
                    if (search != null && search.isEmpty() == false) {
                        for (Student st : search) {
                            list.add(new StudentBean(st.getStudentId(),
                                    st.getFirstName(),
                                    st.getLastName(),
                                    st.getAdmissionDate(),
                                    st.getGroupId(),
                                    studentGroup.getGroupName(),
                                    st.getStatusId(),
                                    statusDao.findById(st.getStatusId()).getStatusName(),
                                    st.getTermId(),
                                    termDao.findById(st.getTermId()).getTermName()
                            ));
                        }
                        m.setMessageType(MessageType.SUCCESS);
                        m.setMessageText("Search results. <a href=\"student\">Back to Student List</a>");
                        request.setAttribute("message", m);
                    }
                }

            } else {
                if (lastName != null && lastName.isEmpty() == false) {
                    List<Student> search = dao.findByLastName(lastName);
                    if (search != null && search.isEmpty() == false) {
                        for (Student st : search) {
                            list.add(new StudentBean(st.getStudentId(),
                                    st.getFirstName(),
                                    st.getLastName(),
                                    st.getAdmissionDate(),
                                    st.getGroupId(),
                                    groupDao.findById(st.getGroupId()).getGroupName(),
                                    st.getStatusId(),
                                    statusDao.findById(st.getStatusId()).getStatusName(),
                                    st.getTermId(),
                                    termDao.findById(st.getTermId()).getTermName()
                            ));
                        }
                        m.setMessageType(MessageType.SUCCESS);
                        m.setMessageText("Search results. <a href=\"student\">Back to Student List</a>");
                        request.setAttribute("message", m);
                    }
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Please change search criteria.");
                    request.setAttribute("message", m);
                }
            }
        } else {
            List<Student> studentList = dao.findAll();
            if (studentList != null && studentList.isEmpty() == false) {

                for (Student st : studentList) {
                    list.add(new StudentBean(st.getStudentId(),
                            st.getFirstName(),
                            st.getLastName(),
                            st.getAdmissionDate(),
                            st.getGroupId(),
                            groupDao.findById(st.getGroupId()).getGroupName(),
                            st.getStatusId(),
                            statusDao.findById(st.getStatusId()).getStatusName(),
                            st.getTermId(),
                            termDao.findById(st.getTermId()).getTermName()
                    ));
                }
            }
        }

        if (list != null && list.isEmpty() == false) {
            request.setAttribute("students", list);
            request.setAttribute("terms", termDao.findAll());
            request.setAttribute("groups", groupDao.findAll());
            request.setAttribute("statusList", statusDao.findAll());
        } else {
            m.setMessageType(MessageType.ERROR);
            m.setMessageText("No data available. <a href=\"student\">Back to Student List</a></h4></p>");
            request.setAttribute("message", m);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/student.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String addedDate = request.getParameter("date");
        String group = request.getParameter("group");
        String status = request.getParameter("status");
        String term = request.getParameter("term");
        String operation = request.getParameter("operation");
        DaoFactory daoFactory = new DaoFactory();
        TermDao termDao = daoFactory.getTermDao();
        StudentGroupDao groupDao = daoFactory.getStudentGroupDao();
        StatusDao statusDao = daoFactory.getStatusDao();
        StudentDao dao = daoFactory.getStudentDao();
        Message m = new Message();
        switch (operation) {
            case "add": {
                boolean isUnique = true;
                for (Student st : dao.findAll()) {
                    if (st.getFirstName().equals(name) && st.getLastName().equals(lastName))
                        isUnique = false;
                }
                if (isUnique) {
                    dao.create(new Student(name,
                            lastName,
                            groupDao.findByName(group).getGroupId(),
                            Date.valueOf(addedDate),
                            statusDao.findByName(status).getStatusId(),
                            termDao.findByName(term).getTermId()));
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Student already exists");
                    request.setAttribute("message", m);
                }
                break;
            }
            case "update": {
                Student student = new Student();
                student.setStudentId(Long.valueOf(id));
                student.setFirstName(name);
                student.setLastName(lastName);
                student.setAdmissionDate(Date.valueOf(addedDate));
                student.setGroupId(groupDao.findByName(group).getGroupId());
                student.setStatusId(statusDao.findByName(status).getStatusId());
                student.setTermId(termDao.findByName(term).getTermId());
                boolean isUnique = true;
                for (Student s : dao.findAll()) {
                    if (s.getLastName().equals(lastName)
                            && s.getFirstName().equals(name)
                            && s.getAdmissionDate().equals(Date.valueOf(addedDate))
                            && s.getGroupId().equals(groupDao.findByName(group).getGroupId())
                            && s.getStatusId().equals(statusDao.findByName(status).getStatusId())
                            && s.getTermId().equals(termDao.findByName(term).getTermId()))
                        isUnique = false;
                }
                if (isUnique) {
                    dao.update(student);
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Student already exists");
                    request.setAttribute("message", m);
                }
                break;
            }
            case "delete": {
                Student del = new Student();
                del.setStudentId(Long.valueOf(id));

                if (dao.delete(del)) {
                    m.setMessageType(MessageType.SUCCESS);
                    m.setMessageText("Success");
                    request.setAttribute("message", m);
                } else {
                    m.setMessageType(MessageType.ERROR);
                    m.setMessageText("Student cannot be deleted");
                    request.setAttribute("message", m);
                }
                break;
            }
        }
        List<StudentBean> list = new ArrayList<>();
        List<Student> studentList = dao.findAll();
        if (studentList != null && studentList.isEmpty() == false) {

            for (Student st : studentList) {
                list.add(new StudentBean(st.getStudentId(),
                        st.getFirstName(),
                        st.getLastName(),
                        st.getAdmissionDate(),
                        st.getGroupId(),
                        groupDao.findById(st.getGroupId()).getGroupName(),
                        st.getStatusId(),
                        statusDao.findById(st.getStatusId()).getStatusName(),
                        st.getTermId(),
                        termDao.findById(st.getTermId()).getTermName()
                ));
            }
        }
        if (list != null && list.isEmpty() == false) {

            request.setAttribute("students", list);
            request.setAttribute("terms", termDao.findAll());
            request.setAttribute("groups", groupDao.findAll());
            request.setAttribute("statusList", statusDao.findAll());
        } else {
            m.setMessageType(MessageType.ERROR);
            m.setMessageText("No data available. <a href=\"student\">Back to Student List</a>");
            request.setAttribute("message", m);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/student.jsp").forward(request, response);
    }

}