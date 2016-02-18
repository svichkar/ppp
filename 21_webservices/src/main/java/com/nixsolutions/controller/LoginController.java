package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.service.OrderInWorkService;
import com.nixsolutions.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private OrderInWorkService orderInWorkServiceImpl;

	@RequestMapping(value = { "/", "/welcome", "/index" }, method = RequestMethod.GET)
	public String hello(Model model) {
		model.addAttribute("title", "CAR STATION");
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		if (SecurityContextHolder.getContext().getAuthentication().getName() != "anonymousUser") {
			User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			com.nixsolutions.entities.User user = userServiceImpl.getByName(authUser.getUsername());
			if (user.getRole().getRoleName().equalsIgnoreCase("admin")) {
				model.addAttribute("oiwcs", orderInWorkServiceImpl.getAllOrderInWorkCarStatus());
				model.addAttribute("title", "Orders");
				return "orders";
			} else {
				model.addAttribute("oiwcs", orderInWorkServiceImpl.getAllOrderInWorkCarStatus());
				model.addAttribute("title", "Orders status");
				return "userpage";
			}
		}
		else
		{
			return "/error";
		}
	}
}
