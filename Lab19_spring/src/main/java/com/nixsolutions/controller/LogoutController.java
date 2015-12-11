package com.nixsolutions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

	@RequestMapping(value = "/logout.do", method = {RequestMethod.GET, RequestMethod.POST})
	protected String logout(Model model) {
		return "/index.html";
	}
}
