package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.studentgrade.service.RoleService;
import com.nixsolutions.studentgrade.service.UserService;

@Controller
public class UsersController {
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/users")
	protected String users(@RequestParam(value = "add", required = false) String add,
			@RequestParam(value = "delete", required = false) String delete,
			@RequestParam(value = "newLogin", required = false) String login,
			@RequestParam(value = "newPassword", required = false) String password,
			@RequestParam(value = "newEmail", required = false) String email,
			@RequestParam(value = "newRole", required = false) Long roleId,
			@RequestParam(value = "user_id", required = false) Long userId,
			@RequestParam(value = "message", required = false) String message,
			Model model) {
		
		if (message != null){
			model.addAttribute("message", message);
		}
			
		if (add != null) {
			model.addAttribute("message", "Added user with id "
					+ userService.createUser(login, password, email, roleId));
		}
		if (delete != null) {
			userService.deleteUser(userId);
			model.addAttribute("message", "Deleted user with id " + userId);
		}		
		
		model.addAttribute("users", userService.findAllUsers());
		model.addAttribute("roles", roleService.findAllRoles());
		return "users";
	}

}
