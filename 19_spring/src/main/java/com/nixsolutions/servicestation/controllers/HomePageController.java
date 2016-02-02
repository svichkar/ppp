
package com.nixsolutions.servicestation.controllers;

import com.nixsolutions.servicestation.entity.Role;
import com.nixsolutions.servicestation.entity.User;
import com.nixsolutions.servicestation.service.CarService;
import com.nixsolutions.servicestation.service.RoleService;
import com.nixsolutions.servicestation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomePageController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/homepage", method = RequestMethod.POST)
    protected String tryToLogin(@RequestParam(value = "login") String login,
                                @RequestParam(value = "pass") String password,
                                Model model) {
        User user = userService.findByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                Role role = roleService.findById(user.getRole().getRoleId());
                model.addAttribute("role", role.getRoleName());
                if (role.getRoleName().equals("manager")) {

                    model.addAttribute("CarOrders", carService.findAll());
                } else {
                    model.addAttribute("CarOrders", carService.getUserCarOrders(login));
                }
                return "homepage";
            } else {
                return "redirect:/index.jsp?message=Your%20login%20is%20wrong";
            }
        } else {
            return "redirect:/index.jsp?message=Your%20login%20is%20wrong%20or%20doesn't%20exist";
        }

    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    protected String getHomePage(Model model) {
        model.addAttribute("CarOrders", carService.findAll());
        return "homepage";
    }

}

