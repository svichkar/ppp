package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam(value = "j_username") String login, @RequestParam("j_password") String password,
			Model model) {
		if (userService.isUserValid(login, password)) {
			model.addAttribute("login", login);
			return "redirect:/nav.do";
		} else {
			return "";
		}
	}

}
