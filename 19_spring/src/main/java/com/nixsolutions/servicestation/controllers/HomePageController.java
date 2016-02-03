
package com.nixsolutions.servicestation.controllers;

import com.nixsolutions.servicestation.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class HomePageController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    protected String getHomePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            model.addAttribute("CarOrders", carService.findAll());
        } else {
            model.addAttribute("CarOrders", carService.getUserCarOrders(user.getUsername()));
        }
        return "homepage";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index.jsp?msg=You've%20successfully%20logged%20out";
    }

}

