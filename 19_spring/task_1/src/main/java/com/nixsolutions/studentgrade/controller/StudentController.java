package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.service.StatusService;
import com.nixsolutions.studentgrade.service.StudentGroupService;
import com.nixsolutions.studentgrade.service.StudentService;
import com.nixsolutions.studentgrade.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by konstantin on 2/1/2016.
 */
@Controller
public class StudentController {

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
    public String searchSubject(@ModelAttribute("lastName") String lastName, @ModelAttribute("group") String group) {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Student Page");
        return "redirect:studentResult";
    }

    @RequestMapping(value = "/studentResult", method = RequestMethod.GET)
    public ModelAndView showResults(@ModelAttribute("lastName") String lastName, @ModelAttribute("group") String group) {

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

}
