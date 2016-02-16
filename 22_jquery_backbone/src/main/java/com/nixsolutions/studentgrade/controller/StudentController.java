package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.*;
import com.nixsolutions.studentgrade.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.List;

/**
 * Created by konstantin on 2/1/2016.
 */
@Controller
public class StudentController {

    private static final Logger LOG = LogManager.getLogger(StudentController.class);

    public static final String URL = "http://localhost:8080/web-services/ws/rest/students";
    private static Client client;
    private static WebTarget service;

    @Autowired
    TermService termService;
    @Autowired
    StudentGroupService groupService;
    @Autowired
    StatusService statusService;
    @Autowired
    JournalService journalService;

    StudentController() {

        client = ClientBuilder.newClient();
        service = client.target(URL);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView studentPage() {

        List<Student> list = service.path("getAllStudents").request().get()
                .readEntity(AllStudentsBean.class).getStudents();

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Student Page");
        model.addObject("students", list);
        model.addObject("groups", groupService.findAll());
        model.addObject("terms", termService.findAll());
        model.addObject("statusList", statusService.findAll());
        model.setViewName("student");
        return model;
    }


    @RequestMapping(value = "/student", params = "operation", method = RequestMethod.GET)
    public String searchStudent(@ModelAttribute("lastName") String lastName,
                                @ModelAttribute("group") String group) {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Student Page");
        return "redirect:studentResult";
    }

    @RequestMapping(value = "/studentResult", method = RequestMethod.GET)
    public ModelAndView showResults(@ModelAttribute("lastName") String lastName,
                                    @ModelAttribute("group") String group) {

        List<Student> studentList = service.path("getStudentByLastNameAndGroup").queryParam("lastName", lastName)
                .queryParam("groupName", group)
                .request().get().readEntity(AllStudentsBean.class).getStudents();

        ModelAndView model = new ModelAndView();
        if (studentList == null || studentList.isEmpty()) {
            model.addObject("message", "No data available. Please change search criteria.");
        }
        model.addObject("students", studentList);
        model.setViewName("studentResult");
        return model;
    }

    @RequestMapping(value = "/student", params = "add", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") Student student,
                             @ModelAttribute("date") String date,
                             @ModelAttribute("selectedGroup") String selectedGroup,
                             @ModelAttribute("selectedTerm") String selectedTerm,
                             @ModelAttribute("selectedStatus") String selectedStatus,
                             Model model) {

        try {
            StudentGroup group = groupService.findByName(selectedGroup);
            Term term = termService.findByName(selectedTerm);
            Status status = statusService.findByName(selectedStatus);
            student.setAdmissionDate(Date.valueOf(date));
            student.setStudentGroup(group);
            student.setTerm(term);
            student.setStatus(status);

            service.path("createStudent").request().post(Entity.entity(student, MediaType.APPLICATION_XML));

            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Student cannot be added");
        }

        List<Student> list = service.path("getAllStudents").request().get()
                .readEntity(AllStudentsBean.class).getStudents();
        model.addAttribute("students", list);

        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("terms", termService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        return "student";
    }

    @RequestMapping(value = "/student", params = "update", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute("student") Student student,
                                @ModelAttribute("date") String date,
                                @ModelAttribute("selectedGroup") String selectedGroup,
                                @ModelAttribute("selectedTerm") String selectedTerm,
                                @ModelAttribute("selectedStatus") String selectedStatus,
                                Model model) {

        try {
            StudentGroup group = groupService.findByName(selectedGroup);
            Term term = termService.findByName(selectedTerm);
            Status status = statusService.findByName(selectedStatus);
            student.setAdmissionDate(Date.valueOf(date));
            student.setStudentGroup(group);
            student.setTerm(term);
            student.setStatus(status);

            service.path("updateStudent").request().put(Entity.entity(student, MediaType.APPLICATION_XML));

            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Student cannot be updated");
        }

        List<Student> list = service.path("getAllStudents").request().get()
                .readEntity(AllStudentsBean.class).getStudents();

        model.addAttribute("students", list);
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("terms", termService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        return "student";
    }

    @RequestMapping(value = "/student", params = "delete", method = RequestMethod.POST)
    public String deleteStudent(@ModelAttribute("studentId") String studentId,
                                Model model) {

        try {
            Student st = service.path("getStudent").path(studentId).request().get().readEntity(Student.class);

            Status inactive = new Status();
            inactive.setStatusId(5L);
            inactive.setStatusName("inactive");
            st.setStatus(inactive);

            service.path("updateStudent").request().put(Entity.entity(st, MediaType.APPLICATION_XML));

            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Student cannot be deleted");
        }

        List<Student> list = service.path("getAllStudents").request().get()
                .readEntity(AllStudentsBean.class).getStudents();

        model.addAttribute("students", list);
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("terms", termService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        return "student";
    }

    @RequestMapping(value = "/student", params = "show", method = RequestMethod.GET)
    public ModelAndView studentDetails(@ModelAttribute("studentId") String studentId,
                                       @ModelAttribute("student") Student student) {

        ModelAndView model = new ModelAndView();

        student = service.path("getStudent").path(studentId).request().get().readEntity(Student.class);

        model.addObject("student", student);
        model.setViewName("redirect:student/journal");
        return model;
    }

    @RequestMapping(value = "/student/journal", method = RequestMethod.GET)
    public ModelAndView journalDetails(@ModelAttribute("studentId") String studentId) {

        ModelAndView model = new ModelAndView();
        Student student = service.path("getStudent").path(studentId).request().get().readEntity(Student.class);
        model.addObject("student", student);
        model.addObject("terms", termService.findAll());
        model.setViewName("studentJournal");
        return model;
    }

    @RequestMapping(value = "/student/journal", params = "operation", method = RequestMethod.GET)
    public ModelAndView showDetails(@ModelAttribute("studentId") String studentId,
                                    @ModelAttribute("term") String term) {

        ModelAndView model = new ModelAndView();
        Term t = termService.findByName(term);
        Student st = service.path("getStudent").path(studentId).request().get().readEntity(Student.class);

        model.addObject("selectedTerm", t);
        model.addObject("student", st);
        model.addObject("journals", journalService.findByStudentAndTerm(Long.valueOf(studentId), t.getTermId()));
        model.addObject("terms", termService.findAll());
        model.addObject("score", journalService.getAverageScore(Long.valueOf(studentId), t.getTermId()));
        model.setViewName("studentJournal");
        return model;
    }
}
