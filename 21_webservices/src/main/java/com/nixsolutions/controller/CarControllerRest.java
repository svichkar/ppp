package com.nixsolutions.controller;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.dto.CarCustomer;
import com.nixsolutions.ws.rest.CarRestWebService;

@Controller
public class CarControllerRest {

	@Autowired
	public CarRestWebService carServiceImpl;

	@RequestMapping(value = { "/carAdd", "/carEdit" }, method = RequestMethod.POST)
	public String processCar(@RequestParam(value = "car_id", required = false) String car_id,
			@RequestParam(value = "action") String action, @RequestParam(value = "vin", required = false) String vin,
			@RequestParam(value = "model", required = false) String carModel,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "customer_id", required = false) String customer_id, Model model) {

		int customerId = NumberUtils.isDigits(customer_id) ? Integer.parseInt(customer_id) : 0;
		int carId = NumberUtils.isDigits(car_id) ? Integer.parseInt(car_id) : 0;

		if (carId > 0) {
			CarCustomer car = carServiceImpl.getCarById(car_id);
			if (action.equalsIgnoreCase("Edit")) {
				model.addAttribute("car", car);
				model.addAttribute("customers", carServiceImpl.getAllCars());
				model.addAttribute("title", "Edit car");
				model.addAttribute("jsForPage", "car");
				return "car_rest";
			} else if (action.equalsIgnoreCase("Delete")) {
				carServiceImpl.deleteCar(car_id);
				model.addAttribute("carcustomers", carServiceImpl.getAllCars());
				model.addAttribute("title", "Cars");
				return "cars";
			} else if (action.equalsIgnoreCase("Save")) {
				car.setModel(carModel);
				car.setVin(vin);
				car.setDescription(description);
				car.setCustomerId(customerId);
				carServiceImpl.updateCar(car);
				model.addAttribute("carcustomers", carServiceImpl.getAllCars());
				model.addAttribute("title", "Cars");
				return "cars";
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				model.addAttribute("customers", carServiceImpl.getAllCars());
				model.addAttribute("title", "Add car");
				model.addAttribute("jsForPage", "car");
				return "car_rest";
			} else if (action.equalsIgnoreCase("Save")) {
				CarCustomer car = new CarCustomer(carModel, vin, description, customerId, "", "");
				carServiceImpl.addCar(car);
				model.addAttribute("carcustomers", carServiceImpl.getAllCars());
				model.addAttribute("title", "Cars");
				return "cars";
			}
		}
		model.addAttribute("carcustomers", carServiceImpl.getAllCars());
		model.addAttribute("title", "Cars");
		return "cars";

	}

}
