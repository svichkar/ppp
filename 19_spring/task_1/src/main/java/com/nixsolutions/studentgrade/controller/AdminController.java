package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.service.RoleService;
import com.nixsolutions.studentgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by konstantin on 1/30/2016.
 */
@Controller
public class AdminController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Admin PAge");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.addObject("users", userService.findAll());
        model.addObject("roles", roleService.findAll());
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ModelAndView addUser() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Admin PAge");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.addObject("users", userService.findAll());
        model.addObject("roles", roleService.findAll());
        model.setViewName("admin");
        return model;
    }

}
