package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.User;
import com.nixsolutions.studentgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by svichkar on 2/1/2016.
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage() {

        ModelAndView model = new ModelAndView();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByLogin(name);
        model.addObject("hello", String.format("%s %s", user.getFirstName(), user.getLastName()));
        model.setViewName("home");
        return model;
    }
}
