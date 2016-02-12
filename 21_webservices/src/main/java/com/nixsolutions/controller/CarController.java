package com.nixsolutions.controller;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.OrderInWorkService;

@Controller
public class CarController {

	@Autowired
	private CarService carServiceImpl;
	@Autowired
	private CustomerService customerServiceImpl;
	@Autowired
	private OrderInWorkService orderInWorkImpl;

	
	@RequestMapping(value = { "/carAdd", "/carEdit" }, method = RequestMethod.POST)
	public String processCar(@RequestParam(value = "car_id", required = false) String car_id,
			@RequestParam(value = "action") String action, @RequestParam(value = "vin", required = false) String vin,
			@RequestParam(value = "model", required = false) String carModel,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "customer_id", required = false) String customer_id, Model model) {

		int customerId = NumberUtils.isDigits(customer_id) ? Integer.parseInt(customer_id) : 0;
		int carId = NumberUtils.isDigits(car_id) ? Integer.parseInt(car_id) : 0;

		Customer customer = null;
		if (customerId > 0) {
			customer = customerServiceImpl.getCustomerById(customerId);
		}

		if (carId > 0) {
			Car car = carServiceImpl.getCarById(carId);
			if (action.equalsIgnoreCase("Edit")) {
				model.addAttribute("car", car);
				model.addAttribute("customers", customerServiceImpl.getAllCustomers());
				model.addAttribute("title", "Edit car");
				model.addAttribute("jsForPage", "car");
				return "car";
			} else if (action.equalsIgnoreCase("Delete")) {
				List<OrderInWork> allOrderInWork = orderInWorkImpl.getAllOrderInWork();
				for (OrderInWork orderInWork : allOrderInWork) {
					if (orderInWork.getCar().getCarId() == car.getCarId()) {
						throw new RuntimeException("You cannot remove car when it is in order!!");
					}
				}
				carServiceImpl.deleteCar(car);
				model.addAttribute("carcustomers", carServiceImpl.getAllCarCustomers());
				model.addAttribute("title", "Cars");
				return "cars";
			} else if (action.equalsIgnoreCase("Save")) {
				car.setModel(carModel);
				car.setVin(vin);
				car.setDescription(description);
				car.setCustomer(customer);
				carServiceImpl.updateCar(car);
				model.addAttribute("carcustomers", carServiceImpl.getAllCarCustomers());
				model.addAttribute("title", "Cars");
				return "cars";
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				model.addAttribute("customers", customerServiceImpl.getAllCustomers());
				model.addAttribute("title", "Add car");
				model.addAttribute("jsForPage", "car");
				return "car";
			} else if (action.equalsIgnoreCase("Save")) {
				Car car = new Car(carModel, vin, description, customer);
				carServiceImpl.addCar(car);
				model.addAttribute("carcustomers", carServiceImpl.getAllCarCustomers());
				model.addAttribute("title", "Cars");
				return "cars";
			}
		}
		model.addAttribute("carcustomers", carServiceImpl.getAllCarCustomers());
		model.addAttribute("title", "Cars");
		return "cars";
	}

}
