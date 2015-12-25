package com.nixsolutions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {


	@RequestMapping(value = "/403")
	public String welcomeMessage(@RequestParam(value = "customer", required = false) String customerId,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {
		return "403";
	}
}
