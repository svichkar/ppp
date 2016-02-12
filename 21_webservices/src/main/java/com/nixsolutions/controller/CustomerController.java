package com.nixsolutions.controller;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.OrderInWorkService;
import com.nixsolutions.service.UserService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerServiceImpl;
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private OrderInWorkService orderInWorkServiceImpl;

	@RequestMapping(value = { "/customerAdd", "/customerEdit" }, method = RequestMethod.POST)
	public String processCustomer(@RequestParam(value = "customer_id", required = false) String customer_id,
			@RequestParam(value = "user_id", required = false) String user_id,
			@RequestParam(value = "action") String action,
			@RequestParam(value = "f_name", required = false) String f_name,
			@RequestParam(value = "l_name", required = false) String l_name,
			@RequestParam(value = "phone", required = false) String phone, Model model) {
		customer_id = customer_id != null ? customer_id : "";
		user_id = user_id != null ? user_id : "";

		int customerId = NumberUtils.isDigits(customer_id) ? Integer.parseInt(customer_id) : 0;
		int userId = NumberUtils.isDigits(user_id) ? Integer.parseInt(user_id) : 0;

		if (customerId > 0) {
			Customer customer = customerServiceImpl.getCustomerById(customerId);
			if (action.equalsIgnoreCase("Edit")) {
				model.addAttribute("customer", customer);
				model.addAttribute("users", userServiceImpl.getAllUsers());
				model.addAttribute("title", "Edit customer");
				model.addAttribute("jsForPage", "customer");
				return "customer";
			} else if (action.equalsIgnoreCase("Delete")) {
				List<OrderInWork> orderInWorks = orderInWorkServiceImpl.getAllOrderInWork();
				for (OrderInWork orderInWork : orderInWorks) {
					if (orderInWork.getCar().getCustomer().getCustomerId() == customer.getCustomerId()) {
						throw new RuntimeException("You cannot remove customer when his/her car in order!!");
					}
				}
				customerServiceImpl.deleteCustomer(customer);
				model.addAttribute("customers", customerServiceImpl.getAllCustomers());
				model.addAttribute("title", "Customers");
				return "customers";
			} else if (action.equalsIgnoreCase("Save")) {
				customer.setFname(f_name);
				customer.setLname(l_name);
				customer.setPhone(phone);
				customer.setUser(userServiceImpl.getUserById(userId));
				customerServiceImpl.updateCustomer(customer);
				model.addAttribute("customers", customerServiceImpl.getAllCustomers());
				model.addAttribute("title", "Customers");
				return "customers";
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				model.addAttribute("title", "Add customer");
				model.addAttribute("users", userServiceImpl.getAllUsers());
				model.addAttribute("jsForPage", "customer");
				return "customer";
			} else if (action.equalsIgnoreCase("Save")) {
				Customer customer = new Customer(f_name, l_name, phone, userServiceImpl.getUserById(userId));
				customerServiceImpl.addCustomer(customer);
				model.addAttribute("customers", customerServiceImpl.getAllCustomers());
				model.addAttribute("title", "Customers");
				return "customers";
			}
		}
		model.addAttribute("customers", customerServiceImpl.getAllCustomers());
		model.addAttribute("title", "Customers");
		return "customers";
	}

}
