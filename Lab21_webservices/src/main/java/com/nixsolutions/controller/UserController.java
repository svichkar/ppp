package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.entity.User;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/AdminHome.do", method = {RequestMethod.GET, RequestMethod.POST})
	protected String userGet(Model model) {
		model.addAttribute("users", userService.getAll());
		return "/WEB-INF/jsp/AdminHome.jsp";
	}

	@RequestMapping(value = "/addNewUser.do", method = RequestMethod.GET)
	protected String addNewUserGet(Model model) {
		return "/WEB-INF/jsp/AddNewUser.jsp";
	}

	@RequestMapping(value = "/addNewUser.do", method = RequestMethod.POST)
	protected String addNewUserPost(@ModelAttribute("login") String userName,
			@ModelAttribute("password") String password, @ModelAttribute("email") String email,
			@ModelAttribute("role") String roleName, Model model) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);
		user.setRole(roleService.getByRoleName(roleName));
		userService.create(user);
		model.addAttribute("users", userService.getAll());
		return "/WEB-INF/jsp/AdminHome.jsp";
	}

	@RequestMapping(value = "/editUser.do", method = RequestMethod.GET)
	protected String editUserGet(@ModelAttribute("userId") String userId, Model model) {
		User user = userService.getByUserId(Integer.parseInt(userId));
		model.addAttribute("user", user);
		model.addAttribute("role", user.getRole());
		return "/WEB-INF/jsp/EditUser.jsp";
	}

	@RequestMapping(value = "/editUser.do", method = RequestMethod.POST)
	protected String editUserPost(@ModelAttribute("userId") String userId, @ModelAttribute("login") String userName,
			@ModelAttribute("password") String password, @ModelAttribute("email") String email,
			@ModelAttribute("role") String roleName, Model model) {
		User user = userService.getByUserId(Integer.parseInt(userId));
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);
		user.setRole(roleService.getByRoleName(roleName));
		userService.update(user);
		model.addAttribute("users", userService.getAll());
		return "/WEB-INF/jsp/AdminHome.jsp";
	}

	@RequestMapping(value = "/deleteUser.do", method = RequestMethod.POST)
	protected String deleteUserPost(@ModelAttribute("userId") String userId, Model model) {
		userService.delete(userService.getByUserId(Integer.parseInt(userId)));
		model.addAttribute("users", userService.getAll());
		return "/WEB-INF/jsp/AdminHome.jsp";
	}
}
