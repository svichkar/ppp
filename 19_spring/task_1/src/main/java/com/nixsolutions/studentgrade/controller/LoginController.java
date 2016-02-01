package com.nixsolutions.studentgrade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by svichkar on 2/1/2016.
 */
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }
}
