package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nixsolutions.studentgrade.service.RoleService;
import com.nixsolutions.studentgrade.service.UserService;

@Controller
public class UpdateUserController {
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@RequestMapping(value = "/updateUser")
	protected String updateUser(@RequestParam(value = "update", required = false) String update,			
			@RequestParam(value = "updatedLogin", required = false) String login,
			@RequestParam(value = "updatedPassword", required = false) String password,
			@RequestParam(value = "updatedEmail", required = false) String email,
			@RequestParam(value = "updatedRole", required = false) Long roleId,
			@RequestParam(value = "user_id", required = false) Long userId,
			RedirectAttributes redirectAttributes,
			Model model) {
		if (update != null) {
			userService.updateUser(userId, login, password, email, roleId);
			redirectAttributes.addAttribute("message", "Updated user with id "+ userId);
			return "redirect:users";			
		}
		model.addAttribute("updateUser", userService.findUserById(userId));
		model.addAttribute("roles", roleService.findAllRoles());
		return "updateUser";
	}
}
