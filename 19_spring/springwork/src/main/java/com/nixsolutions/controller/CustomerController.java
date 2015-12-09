package com.nixsolutions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.OrderPartService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.OrderWorkerService;
import com.nixsolutions.service.UserService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderWorkerService orderWorkerService;
	@Autowired
	private OrderPartService orderPartService;
	@Autowired
	private CarService carService;

	@RequestMapping(value = "/addCustomer.do", method = RequestMethod.GET)
	public String addCustomer(Model model) {
		model.addAttribute("action", "add");
		model.addAttribute("users", userService.getUsersWithRoleUser());
		return "/WEB-INF/jsp/customer.jsp";
	}

	@RequestMapping(value = "/editCustomer.do", method = RequestMethod.POST)
	public String editCustomer(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "customer_id") int customerId, Model model) {
		model.addAttribute("action", "edit");
		model.addAttribute("customer", customerService.getCustomerById(customerId));
		model.addAttribute("users", userService.getUsersWithRoleUser());
		return "/WEB-INF/jsp/customer.jsp";
	}

	@RequestMapping(value = "/deleteCustomer.do", method = RequestMethod.POST)
	public String deleteCustomer(@ModelAttribute(value = "customer_id") int customerId, Model model) {
		Customer customer = customerService.getCustomerById(customerId);
		List<OrderInWork> orderList = orderService.getOrdersByCustomer(customer);
		for (OrderInWork order : orderList) {
			orderWorkerService.getAllOrderWorkersByOrderId(order.getOrderId())
					.forEach(x -> orderWorkerService.deleteOrderWorker(x));
			orderPartService.getOrderPartsByOrderId(order.getOrderId())
					.forEach(x -> orderPartService.deleteOrderPart(x));
			orderService.deleteOrder(order);
		}
		carService.getCarsByCustomer(customer).forEach(x -> carService.deleteCar(x));
		customerService.deleteCustomer(customer);
		return "/nav.do";
	}
}
