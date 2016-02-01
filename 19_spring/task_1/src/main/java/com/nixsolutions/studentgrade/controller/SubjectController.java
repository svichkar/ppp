package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.service.SubjectService;
import com.nixsolutions.studentgrade.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by konstantin on 2/2/2016.
 */
@Controller
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    TermService termService;

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public ModelAndView subjectPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Subject Page");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.addObject("subjects", subjectService.findAll());
        model.addObject("terms", termService.findAll());
        model.setViewName("subject");
        return model;
    }
}
