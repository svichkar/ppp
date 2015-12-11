package com.nixsolutions.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginGet(HttpServletResponse res, HttpServletRequest req, Model model) throws IOException{
		PrintWriter out = res.getWriter();
		String role = (String) req.getSession().getAttribute("role");
		if (role != null) {
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
		} else {
			out.println("<font color=red>Sorry, username or password is wrong.</font>");
		}
		out.close();
		return "";
	}
		
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginPost(@RequestParam(value="login") String userName, @RequestParam("password") String password,
			HttpServletResponse res, HttpServletRequest req, Model model) throws IOException{
		PrintWriter out = res.getWriter();
		if (userService.checkUser(userName, password)) {			
			String role = userService.getByUserName(userName).getRole().getRoleName();
			req.getSession().setAttribute("role", role);
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
		} else {
			out.println("<font color=red>Sorry, username or password is wrong.</font>");
		}
		out.close();
		return "";
	}
}
