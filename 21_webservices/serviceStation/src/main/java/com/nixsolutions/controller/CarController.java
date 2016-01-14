package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.service.CarService;
import com.nixsolutions.service.CustomerService;

@Controller
public class CarController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CarService carService;

	@RequestMapping(value = "/admin/addNewCar", method = { RequestMethod.GET, RequestMethod.POST })
	public String welcomeMessage(@RequestParam(value = "customer", required = false) String customerId,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {

		model.addAttribute("customers", customerService.getAllCustomers());
		model.addAttribute("homePage", homePage);
		return "newCar";
	}

	@RequestMapping(value = "/admin/createNewCar", method = { RequestMethod.GET, RequestMethod.POST })
	public String createNewCar(@RequestParam(value = "customer", required = false) String customerId,
			@RequestParam(value = "regNumber", required = false) String regNumber,
			@RequestParam(value = "carModel", required = false) String carModel,
			@RequestParam(value = "vinNumber", required = false) String vinNumber,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {

		carService.createNewCar(carModel, regNumber, vinNumber, customerService.getCustomerByID(customerId));
		model.addAttribute("homePage", homePage);
		model.addAttribute("car", carService.getCarByRegNumber(regNumber));
		model.addAttribute("customers", customerService.getAllCustomers());
		return "editCar";
	}

	@RequestMapping(value = "/admin/carPage", method = RequestMethod.GET)
	public String loadCarPage(@RequestParam(value = "homePage", required = false) String homePage, Model model) {
		model.addAttribute("carList", carService.getAllCar());
		model.addAttribute("homePage", homePage);
		return "carPage";
	}

	@RequestMapping(value = "/admin/updateCar", method = RequestMethod.POST)
	public String updateCar(@RequestParam(value = "customer_id", required = false) String customerId,
			@RequestParam(value = "regNumber", required = false) String regNumber,
			@RequestParam(value = "carModel", required = false) String carModel,
			@RequestParam(value = "car_description", required = false) String carDescription,
			@RequestParam(value = "vinNumber", required = false) String vinNumber,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {

		carService.updateCar(carModel, regNumber, vinNumber, carDescription,
				customerService.getCustomerByID(customerId));
		if (homePage.equalsIgnoreCase("carPage")) {
			model.addAttribute("carList", carService.getAllCar());
			model.addAttribute("homePage", homePage);
			return "carPage";
		} else if (homePage.equalsIgnoreCase("newOrder")) {
			model.addAttribute("carList", carService.getAllCar());
			return "newOrder";
		}
		return "adminPage";
	}

	@RequestMapping(value = "/admin/updateExistingCar", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateExistingCar(@RequestParam(value = "car_id", required = false) String car_id,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {
		model.addAttribute("homePage", homePage);
		model.addAttribute("car", carService.getCarByID(car_id));
		model.addAttribute("customers", customerService.getAllCustomers());
		return "editCar";
	}

	@RequestMapping(value = "/admin/deleteCar", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteCar(@RequestParam(value = "car_id", required = false) String car_id,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {
		carService.deleteCar(carService.getCarByID(car_id));
		model.addAttribute("carList", carService.getAllCar());
		model.addAttribute("homePage", homePage);
		return "carPage";
	}
}
