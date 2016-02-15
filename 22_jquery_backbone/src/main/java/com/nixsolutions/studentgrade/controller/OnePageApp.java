package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by svichkar on 2/1/2016.
 */
@Controller
public class OnePageApp {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/onePageApp", method = RequestMethod.GET)
    public String onePageApp() {

        return "onePageApp";
    }
}
