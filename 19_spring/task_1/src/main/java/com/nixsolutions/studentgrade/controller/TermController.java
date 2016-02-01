package com.nixsolutions.studentgrade.controller;

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
public class TermController {

    @Autowired
    TermService termService;

    @RequestMapping(value = "/term", method = RequestMethod.GET)
    public ModelAndView termPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Term Page");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.addObject("terms", termService.findAll());
        model.setViewName("term");
        return model;
    }
}
