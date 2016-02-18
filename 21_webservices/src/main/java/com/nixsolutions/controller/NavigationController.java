package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.service.CarService;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.OrderInWorkService;
import com.nixsolutions.service.PartService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.WorkerService;

@Controller
public class NavigationController {

	@Autowired
	private WorkerService workerService;
	@Autowired
	private OrderInWorkService orderInWorkService;
	@Autowired
	private PartService partService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CarService carService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "navigation" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String defineDirection(@ModelAttribute(value = "destination") String modelDestination,
			@RequestParam(value = "destination", required = false) String reqDestination, Model model) {
		String destination = reqDestination != null ? reqDestination : modelDestination;

		switch (destination) {
		case "Workers":
			model.addAttribute("workers", workerService.getAllWorkerStatusSpecification());
			model.addAttribute("title", destination);
			return "workers";
		case "Parts":
			model.addAttribute("parts", partService.getAllPart());
			model.addAttribute("title", destination);
			return "parts";
		case "Customers":
			model.addAttribute("customers", customerService.getAllCustomers());
			model.addAttribute("title", destination);
			return "customers";
		case "Cars":
			model.addAttribute("carcustomers", carService.getAllCarCustomers());
			model.addAttribute("title", destination);
			return "cars";
		case "BackBone":
			model.addAttribute("title", destination);
			return "backbone";
		case "Orders":
			model.addAttribute("oiwcs", orderInWorkService.getAllOrderInWorkCarStatus());
			model.addAttribute("title", destination);
			return "orders";
		case "Users":
			model.addAttribute("users", userService.getAllUsers());
			model.addAttribute("title", destination);
			return "users";
		default:
			model.addAttribute("oiwcs", orderInWorkService.getAllOrderInWorkCarStatus());
			model.addAttribute("title", "Orders");
			return "orders";

		}

	}
}
