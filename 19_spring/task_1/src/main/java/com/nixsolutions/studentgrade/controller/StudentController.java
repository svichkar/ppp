package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.Status;
import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.model.StudentGroup;
import com.nixsolutions.studentgrade.model.Term;
import com.nixsolutions.studentgrade.service.StatusService;
import com.nixsolutions.studentgrade.service.StudentGroupService;
import com.nixsolutions.studentgrade.service.StudentService;
import com.nixsolutions.studentgrade.service.TermService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by konstantin on 2/1/2016.
 */
@Controller
public class StudentController {

    private static final Logger LOG = LogManager.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;
    @Autowired
    TermService termService;
    @Autowired
    StudentGroupService groupService;
    @Autowired
    StatusService statusService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView studentPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Student Page");
        model.addObject("students", studentService.findAll());
        model.addObject("groups", groupService.findAll());
        model.addObject("terms", termService.findAll());
        model.addObject("statusList", statusService.findAll());
        model.setViewName("student");
        return model;
    }


    @RequestMapping(value = "/student", params = "operation", method = RequestMethod.GET)
    public String searchSubject(@ModelAttribute("lastName") String lastName,
                                @ModelAttribute("group") String group) {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Student Page");
        return "redirect:studentResult";
    }

    @RequestMapping(value = "/studentResult", method = RequestMethod.GET)
    public ModelAndView showResults(@ModelAttribute("lastName") String lastName,
                                    @ModelAttribute("group") String group) {

        ModelAndView model = new ModelAndView();
        List<Student> studentList = new ArrayList<>();

        if (lastName != null && !lastName.isEmpty() && group != null && !group.isEmpty()) {
            studentList = studentService.findByLastNameAndGroup(lastName, group);
        } else if (lastName != null && !lastName.isEmpty()) {
            studentList = studentService.findByLastName(lastName);
        } else if (group != null && !group.isEmpty()) {
            studentList = studentService.findByGroup(group);
        } else {
            model.addObject("errorSearch", "Please specify search criteria.");
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
            studentService.create(student);
            model.addAttribute("message", "Successes");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("error", "Student cannot be added");
        }
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("terms", termService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        return "student";
    }

    @RequestMapping(value = "/student", params = "update", method = RequestMethod.POST)
    public String updateTerm(@ModelAttribute("student") Student student,
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
            studentService.update(student);
            model.addAttribute("message", "Successes");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("error", "Student cannot be updated");
        }
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("terms", termService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        return "student";
    }

    @RequestMapping(value = "/student", params = "delete", method = RequestMethod.POST)
    public String deleteTerm(@ModelAttribute("studentId") String studentId,
                             Model model) {

        try {
            Student st = studentService.findById(Long.valueOf(studentId));
            Status inactive = new Status();
            inactive.setStatusId(5L);
            inactive.setStatusName("inactive");
            st.setStatus(inactive);
            studentService.update(st);
            model.addAttribute("message", "Successes");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("error", "Student cannot be deleted");
        }
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("terms", termService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        return "student";
    }
}
