package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.UserService;

@Controller
public class CustomerProcessController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/customerPost.do", method = RequestMethod.POST)
	public String processCustomer(@ModelAttribute(value = "id") String customerId,
			@ModelAttribute(value = "first_name") String firstName,
			@ModelAttribute(value = "last_name") String lastName, @ModelAttribute(value = "phone") String phone,
			@ModelAttribute(value = "user_id") String userId, Model model) {
		Customer customer = customerService.getCustomerById(customerId.equals("") ? 0 : Integer.parseInt(customerId));
		if (customer == null) {
			customer = new Customer(firstName, lastName, phone, userService.getUserById(Integer.parseInt(userId)));
			customerService.addCustomer(customer);
		} else {
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setPhone(phone);
			customer.setUser(userService.getUserById(Integer.parseInt(userId)));
			customerService.updateCustomer(customer);
		}
		model.addAttribute("target", "Customers");
		return "/nav.do";
	}

}
