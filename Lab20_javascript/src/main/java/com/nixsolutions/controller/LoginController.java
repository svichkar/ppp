package com.nixsolutions.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.nixsolutions.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(HttpServletResponse res, HttpServletRequest req, Model model) throws IOException {
		PrintWriter out = res.getWriter();
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String role = userService.getByUserName(authUser.getUsername()).getRole().getRoleName();
		switch (role.toLowerCase()) {
		case "administrator":
			return "redirect:/AdminHome.do";
		case "manager":
			return "/WEB-INF/jsp/ManagerHome.jsp";
		case "teacher":
			out.println("<font color=red>Sorry, not yet ready.</font>");
			break;
		case "student":
			out.println("<font color=red>Sorry, not yet ready.</font>");
			break;
		}
		return "";
	}
	
	 @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	    public String accessDeniedPage(Model model) {
	        return "/WEB-INF/jsp/accessDenied.jsp";
	    }
}
