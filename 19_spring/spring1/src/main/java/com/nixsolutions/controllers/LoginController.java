package com.nixsolutions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String signin() {
		return "Login";
	}
	
	@RequestMapping(value = "/login-failure", method = RequestMethod.GET)
	public String signinFailure() {
		return "login_failure";
	}
	
}
