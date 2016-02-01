package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.service.StatusService;
import com.nixsolutions.studentgrade.service.StudentGroupService;
import com.nixsolutions.studentgrade.service.StudentService;
import com.nixsolutions.studentgrade.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.addObject("students", studentService.findAll());
        model.addObject("groups", groupService.findAll());
        model.addObject("terms", termService.findAll());
        model.addObject("statusList", statusService.findAll());
        model.setViewName("student");
        return model;
    }
}
