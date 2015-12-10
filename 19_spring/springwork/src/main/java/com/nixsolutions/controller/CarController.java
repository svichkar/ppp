package com.nixsolutions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.OrderPartService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.OrderWorkerService;

@Controller
public class CarController {

	@Autowired
	private CarService carService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderWorkerService orderWorkerService;
	@Autowired
	private OrderPartService orderPartService;

	@RequestMapping(value = "/addCar.do", method = RequestMethod.GET)
	public String addCar(Model model) {
		model.addAttribute("action", "add");
		model.addAttribute("customers", customerService.getAllCustomers());
		return "/WEB-INF/jsp/car.jsp";
	}

	@RequestMapping(value = "/editCar.do", method = RequestMethod.POST)
	public String editCar(@ModelAttribute(value = "action") String action, @ModelAttribute(value = "car_id") int carId,
			Model model) {
		model.addAttribute("car", carService.getCarById(carId));
		model.addAttribute("action", "edit");
		model.addAttribute("customers", customerService.getAllCustomers());
		return "/WEB-INF/jsp/car.jsp";
	}

	@RequestMapping(value = "/deleteCar.do", method = RequestMethod.POST)
	public String deleteCar(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "car_id") int carId, Model model) {
		Car car = carService.getCarById(carId);
		List<OrderInWork> orderList = orderService.getOrdersByCar(car);
		for (OrderInWork order : orderList) {
			orderWorkerService.getOrderWorkersByOrderId(order.getOrderId())
					.forEach(x -> orderWorkerService.deleteOrderWorker(x));
			orderPartService.getOrderPartsByOrderId(order.getOrderId())
					.forEach(x -> orderPartService.deleteOrderPart(x));
			orderService.deleteOrder(order);
		}
		carService.deleteCar(car);
		return "/nav.do";
	}

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
