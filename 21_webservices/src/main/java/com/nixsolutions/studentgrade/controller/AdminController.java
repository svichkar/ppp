package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.Role;
import com.nixsolutions.studentgrade.model.User;
import com.nixsolutions.studentgrade.service.RoleService;
import com.nixsolutions.studentgrade.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
            if (userService.isUnique(user)) {
                userService.create(user);
                model.addObject("message", "Success");
                model.addObject("color", "color:#15DC13");
            } else {
                model.addObject("message", "User with this login OR email already exists");
            }
        } catch (Exception e) {
            LOG.error(e);
            model.addObject("message", "User can't be added");
        }

        model.addObject("users", userService.findAll());
        model.addObject("roles", roleService.findAll());
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/admin", params = "delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(@ModelAttribute("user") User user) {

        ModelAndView model = new ModelAndView();
        try {
            Role role = userService.findById(user.getUserId()).getRole();
            if (!role.getRoleName().toLowerCase().equals("admin")) {
                userService.delete(user);
                model.addObject("message", "Success");
                model.addObject("color", "color:#15DC13");
            } else {
                model.addObject("message", "User with admin rights can't be deleted");
            }
        } catch (Exception e) {
            LOG.error(e);
            model.addObject("message", "User can't be deleted");
        }

        model.addObject("users", userService.findAll());
        model.addObject("roles", roleService.findAll());
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/admin", params = "update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("user") User user,
                                   @ModelAttribute("selectedRole") String selectedRole) {

        ModelAndView model = new ModelAndView();
        Role role = roleService.findByName(selectedRole);
        user.setRole(role);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User owner = userService.findByLogin(name);
        try {
            if (!owner.getUserId().equals(user.getUserId())) {
                userService.update(user);
                model.addObject("message", "Success");
                model.addObject("color", "color:#15DC13");
            } else {
                model.addObject("message", "User can't be updated by himself");
            }
        } catch (Exception e) {
            LOG.error(e);
            model.addObject("message", "User can't be updated");
        }

        model.addObject("users", userService.findAll());
        model.addObject("roles", roleService.findAll());
        model.setViewName("admin");
        return model;
    }

}
