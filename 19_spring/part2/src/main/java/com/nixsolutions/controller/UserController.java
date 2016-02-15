package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.User;
import com.nixsolutions.error.CustomException;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private CustomerService customerServiceImpl;
	@Autowired
	private RoleService roleServiceImpl;

	@RequestMapping(value = { "/userAdd", "/userEdit" }, method = RequestMethod.POST)
	public String processUser(@RequestParam(value = "user_id", required = false) String user_id,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "role_id", required = false) String role_id,
			@RequestParam(value = "action") String action,
			@RequestParam(value = "username", required = false) String username, Model model) {

		user_id = user_id != null ? user_id : "";
		role_id = role_id != null ? role_id : "";
		/// ids
		int userId = NumberUtils.isDigits(user_id) ? Integer.parseInt(user_id) : 0;
		int roleId = NumberUtils.isDigits(role_id) ? Integer.parseInt(role_id) : 0;

		if (userId > 0) {
			User user = userServiceImpl.getUserById(userId);
			if (action.equalsIgnoreCase("Edit")) {
				model.addAttribute("user", user);
				model.addAttribute("roles", roleServiceImpl.getAllRoles());
				model.addAttribute("title", "Edit user");
				model.addAttribute("jsForPage", "user");
				return "user";
			} else if (action.equalsIgnoreCase("Delete")) {
				List<Customer> customers = customerServiceImpl.getAllCustomers();
				for (Customer customer : customers) {
					if (customer.getUser() != null && customer.getUser().getUserId() == user.getUserId()) {
						throw new CustomException("403",
								"You cannot remove user that is assigned to at least one customer!!");
					}
				}
				if (!user.getRole().getRoleName().equalsIgnoreCase("admin")) {
					userServiceImpl.deleteUser(user);
				}
				return "navigation";
			} else if (action.equalsIgnoreCase("Save")) {
				user.setUsername(username);
				user.setPassword(password);
				user.setRole(roleServiceImpl.findRoleByid(roleId));
				userServiceImpl.updateUser(user);
				model.addAttribute("users", userServiceImpl.getAllUsers());
				model.addAttribute("title", "Users");
				return "users";
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				model.addAttribute("roles", roleServiceImpl.getAllRoles());
				model.addAttribute("title", "Add user");
				model.addAttribute("jsForPage", "user");
				return "user";
			} else if (action.equalsIgnoreCase("Save")) {
				User userNew = new User(username, password, roleServiceImpl.findRoleByid(roleId));
				userServiceImpl.addUser(userNew);
				model.addAttribute("users", userServiceImpl.getAllUsers());
				model.addAttribute("title", "Users");
				return "users";
			}
		}
		model.addAttribute("users", userServiceImpl.getAllUsers());
		model.addAttribute("title", "Users");
		return "users";
	}
}
