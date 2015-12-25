/**
 * 
 */
package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.User;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;

/**
 * @author mixeyes
 *
 */
@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private RoleService userRoleService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/admin/customerPage", method = RequestMethod.GET)
	public String loadCustomerPage(Model model) {
		model.addAttribute("customerList", customerService.getAllCustomers());
		return "customerPage";
	}

	@RequestMapping(value = "/admin/addNewCustomer", method = RequestMethod.GET)
	public String addNewCustomer(Model model) {
//		model.addAttribute("customerList", customerService.getAllCustomers());
		return "newCustomer";
	}

	@RequestMapping(value = "/admin/createNewCustomer", method = RequestMethod.POST)
	public String createNewCustomer(@RequestParam(value = "last_name", required = false) String last_name,
			@RequestParam(value = "first_name", required = false) String first_name,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "userLogin", required = false) String userLogin,
			@RequestParam(value = "userPassword", required = false) String userPassword,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {
		userService.createNewUser(userLogin, userPassword, userRoleService.getUserRole("ROLE_CUSTOMER"));
		Customer customer = new Customer();
		customer.setFirst_name(first_name);
		customer.setLast_name(last_name);
		customer.setPhone(phone);
		customer.setUser(userService.getUserByLogin(userLogin));
		customerService.createNewCustomer(customer);
		model.addAttribute("customerList", customerService.getAllCustomers());
		return "customerPage";
	}

	@RequestMapping(value = "/admin/updateExistingCustomer", method = RequestMethod.POST)
	public String updateExistingCustomer(@RequestParam(value = "customer_id", required = false) String customer_id,
			Model model) {
		model.addAttribute("customer", customerService.getCustomerByID(customer_id));
		return "editCustomer";
	}

	@RequestMapping(value = "/admin/updateCustomer", method = RequestMethod.POST)
	public String updateCustomer(@RequestParam(value = "last_name", required = false) String last_name,
			@RequestParam(value = "first_name", required = false) String first_name,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "user_login", required = false) String userLogin,
			@RequestParam(value = "user_password", required = false) String userPassword,
			@RequestParam(value = "homePage", required = false) String homePage,
			@RequestParam(value = "customer_id", required = false) String customer_id,
			@RequestParam(value = "user_id", required = false) String user_id, Model model) {
		User user = userService.getUserByID(user_id);
		user.setUser_login(userLogin);
		user.setUser_password(userPassword);
		userService.updateUser(user);
		Customer customer = customerService.getCustomerByID(customer_id);
		customer.setFirst_name(first_name);
		customer.setLast_name(last_name);
		customer.setPhone(phone);
		customer.setUser(user);
		customerService.updateCustomer(customer);
		model.addAttribute("customerList", customerService.getAllCustomers());
		return "customerPage";
	}

	@RequestMapping(value = "/admin/deleteCustomer", method = RequestMethod.POST)
	public String deleteCustomer(@RequestParam(value = "customer_id", required = false) String customer_id,
			@RequestParam(value = "user_id", required = false) String user_id, Model model) {
		Customer customer = customerService.getCustomerByID(customer_id);
		customerService.deleteCustomer(customer);
		User user = userService.getUserByID(customer.getUser().getUser_id().intValue());
		userService.deleteUser(user);
		model.addAttribute("customerList", customerService.getAllCustomers());
		return "customerPage";
	}

}
