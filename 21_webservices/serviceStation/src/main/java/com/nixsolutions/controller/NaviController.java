/**
 * 
 */
package com.nixsolutions.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.service.OrderService;

/**
 * @author mixeyes
 *
 */
@Controller
public class NaviController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/navigation", method = RequestMethod.GET)
	public String navigation(Model model) {
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		switch (authUser.getAuthorities().toString()) {
		case "[ROLE_MANAGER]":
			model.addAttribute("orders", orderService.getAllOrders());
			return "adminPage";
		case "[ROLE_CUSTOMER]":
			model.addAttribute("orders", orderService.getOrdersByUserName(authUser.getUsername()));
			return "customerOrder";
		default:
			return "redirect:index.jsp";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:index.jsp";

	}
}
