package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.Role;
import com.nixsolutions.studentgrade.model.User;
import com.nixsolutions.studentgrade.service.RoleService;
import com.nixsolutions.studentgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by konstantin on 1/30/2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showAdmin (@ModelAttribute("users") List<User> users, @ModelAttribute("roles") List<Role> roles, Model model ) {
        roles = roleService.findAll();
        users= userService.findAll();

        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String addUser () {return null;};

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to mysite");
        return "home";
    }

}
