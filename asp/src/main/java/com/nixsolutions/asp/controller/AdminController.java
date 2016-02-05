package com.nixsolutions.asp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.asp.service.RoleService;
import com.nixsolutions.asp.service.UserService;
import com.nixsolutions.asp.entity.User;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	protected String userGet(Model model) {
		model.addAttribute("users", userService.getAll());
		return "admin/adminHome";
	}

	@RequestMapping(value = "/add-new-user", method = RequestMethod.GET)
	protected String addNewUserGet(Model model) {
		model.addAttribute("roleList", roleService.getRoleMap());
		return "admin/addNewUser";
	}

	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	protected String createUserPost(@ModelAttribute("SpringWeb")User user, Model model) {
		userService.create(user);
		model.addAttribute("users", userService.getAll());
		return "admin/adminHome";
	}

	@RequestMapping(value = "/edit-user", method = RequestMethod.GET)
	protected String editUserGet(@ModelAttribute("userId") String userId, Model model) {
		User user = userService.getByUserId(Integer.parseInt(userId));;
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleService.getRoleMap());
		return "admin/editUser";
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	protected String updateUserPost(@ModelAttribute("SpringWeb")User user, Model model) {
		userService.update(user);
		model.addAttribute("users", userService.getAll());
		return "admin/adminHome";
	}

	@RequestMapping(value = "/delete-user", method = RequestMethod.POST)
	protected String deleteUserPost(@ModelAttribute("userId") String userId, Model model) {
		userService.delete(userService.getByUserId(Integer.parseInt(userId)));
		//model.addAttribute("users", userService.getAll());
		//return "admin/adminHome";
		return "redirect:/admin/users";
	}

}
