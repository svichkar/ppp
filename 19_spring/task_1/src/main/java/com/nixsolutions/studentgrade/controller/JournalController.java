package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.service.GradeService;
import com.nixsolutions.studentgrade.service.JournalService;
import com.nixsolutions.studentgrade.service.StudentGroupService;
import com.nixsolutions.studentgrade.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by konstantin on 2/2/2016.
 */
@Controller
public class JournalController {

    @Autowired
    JournalService journalService;
    @Autowired
    GradeService gradeService;
    @Autowired
    StudentGroupService groupService;
    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/journal", method = RequestMethod.GET)
    public ModelAndView journalPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Journal Page");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.addObject("journals", journalService.findAll());
        model.addObject("grades", gradeService.findAll());
        model.addObject("groups", groupService.findAll());
        model.addObject("subjects", subjectService.findAll());
        model.setViewName("journal");
        return model;
    }
}
