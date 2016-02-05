
package com.nixsolutions.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;

@Controller
@RequestMapping("/manageusers")
public class ManageUsersController {
	private static final Logger LOG = LogManager.getLogger();	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String retrieveUsers(Model model){
		List<User> users = userService.getAllUsers();
		List<Role> allRoles = roleService.getAllRoles();

		model.addAttribute("users", users);
		model.addAttribute("roles", allRoles);
		
		return "ManageUsers";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String searchBooks(
			@RequestParam("username")String usr,
			@RequestParam("password")String pswd,
			@RequestParam("selectrole")String roleName,
			@RequestParam(value = "userid", required = false)String userId,
			@RequestParam("button")String buttnName,
			Model model){
		LOG.entry("User name: " + usr + "; pass: " + pswd + "; role: " + roleName + "; usrId: "
				+ userId + "; button: " + buttnName);
		
		if (buttnName.equals("edit user")) {		
			userService.updateUser(userId, roleName, usr, pswd);
		}

		if (buttnName.equals("delete user")) {
			User delUser = userService
					.getUserById(Long.parseLong(userId));
			userService.deleteUser(delUser);
		}

		if (buttnName.equals("create user")) {
			userService.createUser(roleName, usr, pswd);
		}		

		return "redirect:/manageusers";
	}
}
