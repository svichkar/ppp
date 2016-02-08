package com.nixsolutions.asp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	protected ModelAndView addNewUserGet(@RequestParam(value = "error", required = false) String error) {		
		User user = new User();
		ModelAndView model = new ModelAndView("admin/addNewUser", "UserModel", user);
		if (error != null) {
			model.addObject("error", "User with such username already exists!");
		}
		model.addObject("roleList", roleService.getRoleMap());		
		return model;
	}

	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	protected String createUserPost(@ModelAttribute("UserModel")User user, Model model) {
		if(userService.checkUser(user.getUserName(), user.getPassword())){
			model.addAttribute("error", "error");
			return "redirect:/admin/add-new-user";
		}		
		userService.create(user);
		model.addAttribute("users", userService.getAll());
		return "admin/adminHome";
	}

	@RequestMapping(value = "/edit-user", method = RequestMethod.GET)
	protected ModelAndView editUserGet(@ModelAttribute("userId") String userId) {
		User user = userService.getByUserId(Integer.parseInt(userId));
		ModelAndView model = new ModelAndView("admin/editUser", "UserModel", user);
		model.addObject("roleList", roleService.getRoleMap());
		return model;
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	protected String updateUserPost(@ModelAttribute("UserModel")User user, Model model) {
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
