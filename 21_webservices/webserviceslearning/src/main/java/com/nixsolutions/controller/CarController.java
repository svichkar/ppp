package com.nixsolutions.controller;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.OrderPartService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.OrderWorkerService;

@Controller
public class CarController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderWorkerService orderWorkerService;
	@Autowired
	private OrderPartService orderPartService;
	
	private Client wClient = ClientBuilder.newClient();
	private static final String SERVICE_URL = "http://localhost:8080/webserviceslearning/services/car";

	@RequestMapping(value = "/admin/addCar.do", method = RequestMethod.GET)
	public String addCar(Model model) {
		model.addAttribute("action", "add");
		model.addAttribute("customers", customerService.getAllCustomers());
		return "/WEB-INF/jsp/car.jsp";
	}

	@RequestMapping(value = "/admin/editCar.do", method = RequestMethod.POST)
	public String editCar(@ModelAttribute(value = "action") String action, @ModelAttribute(value = "car_id") int carId,
			Model model) {
		Response resp = wClient.target(SERVICE_URL)
				.path("/getById")
				.queryParam("carId", carId)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get();
		model.addAttribute("car", resp.readEntity(Car.class));
		model.addAttribute("action", "edit");
		model.addAttribute("customers", customerService.getAllCustomers());
		return "/WEB-INF/jsp/car.jsp";
	}

	@RequestMapping(value = "/admin/deleteCar.do", method = RequestMethod.POST)
	public String deleteCar(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "car_id") int carId, Model model) {
		Response resp = wClient.target(SERVICE_URL)
				.path("/getById")
				.queryParam("carId", carId)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get();
		Car car = resp.readEntity(Car.class);
		List<OrderInWork> orderList = orderService.getOrdersByCarId(carId);
		for (OrderInWork order : orderList) {
			orderWorkerService.getOrderWorkersByOrderId(order.getOrderId())
					.forEach(x -> orderWorkerService.deleteOrderWorker(x));
			orderPartService.getOrderPartsByOrderId(order.getOrderId())
					.forEach(x -> orderPartService.deleteOrderPart(x));
			orderService.deleteOrder(order);
		}
		wClient.target(SERVICE_URL)
				.path("/delete")
				.resolveTemplate("car", car)
				.request()
				.post(Entity.entity(car, MediaType.APPLICATION_XML));
		return "/nav.do";
	}

	@RequestMapping(value = "/admin/carPost.do", method = RequestMethod.POST)
	public String processCar(@ModelAttribute(value = "id") String carId,
			@ModelAttribute(value = "description") String carDescription,
			@ModelAttribute(value = "model") String carModel, @ModelAttribute(value = "vin") String carVin,
			@ModelAttribute(value = "customer_id") String customerId, Model model) {
		Response resp = wClient.target("http://localhost:8080/webserviceslearning/services/car/getById")
				.queryParam("carId", carId)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get();
		Car car = resp.readEntity(Car.class);
		if (car == null) {
			car = new Car(carModel, carVin, carDescription,
					customerService.getCustomerById(Integer.parseInt(customerId)));
			wClient.target(SERVICE_URL)
					.path("/add")
					.resolveTemplate("car", car)
					.request()
					.post(Entity.entity(car, MediaType.APPLICATION_XML));
		} else {
			car.setModel(carModel);
			car.setVin(carVin);
			car.setDescription(carDescription);
			car.setCustomer(customerService.getCustomerById(Integer.parseInt(customerId)));
			wClient.target(SERVICE_URL)
					.path("/update")
					.resolveTemplate("car", car)
					.request()
					.post(Entity.entity(car, MediaType.APPLICATION_XML));
		}
		model.addAttribute("target", "Cars");
		return "/nav.do";
	}
}
