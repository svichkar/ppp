package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entity.Car;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.CustomerService;

@Controller
public class CarController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CarService carService;

	@RequestMapping(value = "/addNewCar", method = { RequestMethod.GET, RequestMethod.POST })
	public String welcomeMessage(@RequestParam(value = "target", required = false) String target,
			@RequestParam(value = "customer", required = false) String customerId,
			@RequestParam(value = "regNumber", required = false) String regNumber,
			@RequestParam(value = "carModel", required = false) String carModel,
			@RequestParam(value = "vinNumber", required = false) String vinNumber,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {

		if (target.equalsIgnoreCase("car")) {
			model.addAttribute("customers", customerService.getAllCustomers());
			model.addAttribute("homePage", homePage);
			return "newCar";
		} else if (target.equalsIgnoreCase("Create Car")) {
			Car car = new Car();
			car.setCar_model(carModel);
			car.setCustomer(customerService.getCustomerByID(customerId));
			car.setReg_number(regNumber);
			car.setVin_number(vinNumber);
			carService.createNewCar(car);
			model.addAttribute("homePage", homePage);
			model.addAttribute("car", carService.getCarByRegNumber(regNumber));
			model.addAttribute("customers", customerService.getAllCustomers());
			return "editCar";
		} else {
			model.addAttribute("message", "Incorrect data");
			return "newCar";
		}

	}

	@RequestMapping(value = "/carPage", method = RequestMethod.GET)
	public String loadCarPage(@RequestParam(value = "homePage", required = false) String homePage, Model model) {
		model.addAttribute("carList", carService.getAllCar());
		model.addAttribute("homePage", homePage);
		return "carPage";
	}

	@RequestMapping(value = "/updateCar", method = RequestMethod.POST)
	public String updateCar(@RequestParam(value = "customer_id", required = false) String customerId,
			@RequestParam(value = "regNumber", required = false) String regNumber,
			@RequestParam(value = "carModel", required = false) String carModel,
			@RequestParam(value = "car_description", required = false) String car_description,
			@RequestParam(value = "vinNumber", required = false) String vinNumber,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {
		Car car = carService.getCarByRegNumber(regNumber);
		car.setCar_model(carModel);
		car.setCustomer(customerService.getCustomerByID(customerId));
		car.setReg_number(regNumber);
		car.setVin_number(vinNumber);
		car.setCar_description(car_description);
		carService.updateCarByID(car);
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

	@RequestMapping(value = "/updateExistingCar", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateExistingCar(@RequestParam(value = "car_id", required = false) String car_id,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {
		model.addAttribute("homePage", homePage);
		model.addAttribute("car", carService.getCarByID(car_id));
		model.addAttribute("customers", customerService.getAllCustomers());
		return "editCar";
	}

	@RequestMapping(value = "/deleteCar", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteCar(@RequestParam(value = "car_id", required = false) String car_id,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {
		carService.deleteCar(carService.getCarByID(car_id));
		model.addAttribute("carList", carService.getAllCar());
		model.addAttribute("homePage", homePage);
		return "carPage";
	}
}
