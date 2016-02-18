package com.nixsolutions.asp.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.asp.entity.User;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	private Client wClient = ClientBuilder.newClient();
	private static final String SERVICE_URL = "http://localhost:8080/backbone/rest";
	
	@RequestMapping(value = "/backbone", method = RequestMethod.GET)
	protected String backboneGet(Model model) {			
		return "admin/admin";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	protected String userGet(Model model) {		
		List<User> list = wClient.target(SERVICE_URL)
				.path("/user/getAll")
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<User>>(){});		
		model.addAttribute("users", list);			
		return "admin/adminHome";
	}

	@RequestMapping(value = "/add-new-user", method = RequestMethod.GET)
	protected ModelAndView addNewUserGet(@RequestParam(value = "error", required = false) String error) {
		User user = new User();
		ModelAndView model = new ModelAndView("admin/addNewUser", "UserModel", user);
		if (error != null) {
			model.addObject("error", "User with such username already exists!");
		}		
		 Map<String, String> map = wClient.target(SERVICE_URL)
					.path("/role/getAll")
					.request()
					.accept(MediaType.APPLICATION_JSON)
					.get(new GenericType<Map<String, String>>(){});		
		 model.addObject("roleList", map);
		return model;
	}

	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	protected String createUserPost(@ModelAttribute("UserModel") User user, Model model) {
		wClient.target(SERVICE_URL)
		.path("/user/create")
		.resolveTemplate("user", user)
		.request()
		.post(Entity.entity(user, MediaType.APPLICATION_JSON));
		return "redirect:/admin/users";
	}

	@RequestMapping(value = "/edit-user", method = RequestMethod.GET)
	protected ModelAndView editUserGet(@ModelAttribute("userId") String userId) {
		Response resp = wClient.target(SERVICE_URL)
				.path("/user/getById")
				.queryParam("userId", userId)
				.request()
				.get();
		User user = resp.readEntity(User.class);		
		ModelAndView model = new ModelAndView("admin/editUser", "UserModel", user);
		Map<String, String> map = wClient.target(SERVICE_URL)
				.path("/role/getAll")
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<Map<String, String>>(){});	
	 model.addObject("roleList", map);
		return model;
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	protected String updateUserPost(@ModelAttribute("UserModel") User user, Model model) {
		wClient.target(SERVICE_URL)
		.path("/user/update")
		.resolveTemplate("user", user)
		.request()
		.post(Entity.entity(user, MediaType.APPLICATION_JSON));
		return "redirect:/admin/users";
	}

	@RequestMapping(value = "/delete-user", method = RequestMethod.POST)
	protected String deleteUserPost(@ModelAttribute("userId") String userId, Model model) { 			
		wClient.target(SERVICE_URL)
		.path("/user/delete")
		.queryParam("userId", Integer.parseInt(userId))
		.request()
		.delete();
		return "redirect:/admin/users";
	}
}
