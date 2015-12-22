/**
 * 
 */
package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.UserService;

/**
 * @author mixeyes
 *
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView welcomeMessage(@RequestParam(value = "login", required = false) String login,
			@RequestParam(value = "password", required = false) String password) {
		// Name of your jsp file as parameter
		ModelAndView view = null;
		if (userService.validate(login, password)) {
			
			view = new ModelAndView("adminPage");
			view.addObject("orders", orderService.getAllOrders());
		} else {
			view = new ModelAndView("redirect:index.jsp");
		}
		return view;
	}
}
