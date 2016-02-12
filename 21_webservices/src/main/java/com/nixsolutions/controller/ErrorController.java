package com.nixsolutions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {

	@ExceptionHandler(RuntimeException.class)
	@RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
	public String processError(RuntimeException ex, Model model) {
		//need to implement in other way
		model.addAttribute("ex", ex.getMessage());
		model.addAttribute("title", "Oooups! Error!");
		return "errorpage";
	}

}
