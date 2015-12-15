package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.PartService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.WorkerService;

@Controller
public class NavigationController {

	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CarService carService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PartService partService;
	@Autowired
	private WorkerService workerService;

	@RequestMapping(value = "/nav.do", method = RequestMethod.GET)
	public String initialNavigation(@RequestParam(value = "login") String login, Model model) {
		String role = userService.getUserRole(login);
		if (role.equals("Administrator")) {
			model.addAttribute("orders", orderService.getAllOrdersAsBeans());
			return "/WEB-INF/jsp/ordersPage.jsp";
		} else if (role.equals("User")) {
			User user = userService.getUserByLogin(login);
			model.addAttribute("orders", orderService.getOrdersAsBeansByUserId(user.getUserId()));
			return "/WEB-INF/jsp/userPage.jsp";
		} else {
			return "";
		}
	}

	@RequestMapping(value = "/nav.do", method = RequestMethod.POST)
	public String furtherNavigation(@ModelAttribute(value = "login") String login,
			@RequestParam(value = "target", required = false) String targetParam,
			@ModelAttribute(value = "target") String targetAttr, Model model) {
		String target = targetParam != null ? targetParam : targetAttr;
		if (target == null || target.equals("Orders")) {
			model.addAttribute("orders", orderService.getAllOrdersAsBeans());
			return "/WEB-INF/jsp/ordersPage.jsp";
		} else if (target.equals("Cars")) {
			model.addAttribute("cars", carService.getAllCarsAsBeans());
			return "WEB-INF/jsp/carsPage.jsp";
		} else if (target.equals("Customers")) {
			model.addAttribute("customers", customerService.getAllCustomers());
			return "WEB-INF/jsp/customersPage.jsp";
		} else if (target.equals("Parts")) {
			model.addAttribute("parts", partService.getAllParts());
			return "WEB-INF/jsp/partsPage.jsp";
		} else if (target.equals("Workers")) {
			model.addAttribute("workers", workerService.getAllWorkersAsBeans());
			return "WEB-INF/jsp/workersPage.jsp";
		}
		return "";
	}
}
