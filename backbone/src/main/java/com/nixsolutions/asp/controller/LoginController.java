package com.nixsolutions.asp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private Client wClient = ClientBuilder.newClient();
	private static final String SERVICE_URL = "http://localhost:8080/backbone/rest";
	
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
		Response resp = wClient.target(SERVICE_URL)
				.path("/user/getByName")
				.queryParam("userName", authUser.getUsername())
				.request()
				.get();
		com.nixsolutions.asp.entity.User user = resp.readEntity(com.nixsolutions.asp.entity.User.class);	
		String role = user.getRole().getRoleName();
		switch (role.toLowerCase()) {
		case "administrator":
			return "redirect:/admin/users";
		case "manager":
			return "manager/managerHome";
		case "teacher":
		case "student":
			model.addAttribute("error", "Sorry, the application is not ready for your operation!");
			return "login";
		default:
			model.addAttribute("error", "Your role is not defined in the system. Please refer to the administrator.!");
			return "login";
		}
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
	  ModelAndView model = new ModelAndView();
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }		
	  model.setViewName("403");
	  return model;

	}
}
