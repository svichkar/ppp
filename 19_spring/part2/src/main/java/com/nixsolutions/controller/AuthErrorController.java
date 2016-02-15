package com.nixsolutions.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthErrorController {

	@RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("ex", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("ex", "Wrong credentials! Please define correct login and password!");
		}
		model.addObject("code", "403");
		model.addObject("title", "Auth error");
		model.setViewName("noauthpage");
		return model;

	}
}
