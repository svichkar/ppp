package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.Role;
import com.nixsolutions.studentgrade.model.User;
import com.nixsolutions.studentgrade.service.RoleService;
import com.nixsolutions.studentgrade.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by konstantin on 1/30/2016.
 */
@Controller
public class AdminController {

    private static final Logger LOG = LogManager.getLogger(AdminController.class);

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("users", userService.findAll());
        model.addObject("roles", roleService.findAll());
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/admin", params = "add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user,
                                @ModelAttribute("selectedRole") String selectedRole) {

        ModelAndView model = new ModelAndView();
        Role role = roleService.findByName(selectedRole);
        user.setRole(role);
        try {
            userService.create(user);
            model.addObject("message", "Success");
        } catch (Exception e) {
            model.addObject("message", "User can't be added");
        }

        model.addObject("users", userService.findAll());
        model.addObject("roles", roleService.findAll());
        model.setViewName("admin");
        return model;
    }

}
