package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.CustomerService;

@Controller
public class CarProcessController {

	@Autowired
	private CarService carService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/carPost.do", method = RequestMethod.POST)
	public String processCar(@ModelAttribute(value = "id") String carId,
			@ModelAttribute(value = "description") String carDescription,
			@ModelAttribute(value = "model") String carModel, @ModelAttribute(value = "vin") String carVin,
			@ModelAttribute(value = "customer_id") String customerId, Model model) {
		Car car = carService.getCarById(carId.equals("") ? 0 : Integer.parseInt(carId));
		if (car == null) {
			car = new Car(carModel, carVin, carDescription,
					customerService.getCustomerById(Integer.parseInt(customerId)));
			carService.addCar(car);
		} else {
			car.setModel(carModel);
			car.setVin(carVin);
			car.setDescription(carDescription);
			car.setCustomer(customerService.getCustomerById(Integer.parseInt(customerId)));
			carService.updateCar(car);
		}
		model.addAttribute("target", "Cars");
		return "/nav.do";
	}

}
