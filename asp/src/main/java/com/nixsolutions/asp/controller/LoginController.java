package com.nixsolutions.asp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.asp.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String loginSuccess(HttpServletResponse res, HttpServletRequest req, Model model) throws IOException {
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String role = userService.getByUserName(authUser.getUsername()).getRole().getRoleName();
		switch (role.toLowerCase()) {
		case "administrator":
			return "redirect:/admin/users";
		case "manager":
			return "manager-home";
		case "teacher":
		case "student":
			model.addAttribute("error", "Sorry, the application is not ready for your operation!");
			return "/";
		default:
			model.addAttribute("error", "Your role is not defined in the system. Please refer to the administrator.!");
			return "/";
		}
	}
}
