package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.OrderPartService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.OrderStatusService;
import com.nixsolutions.service.OrderWorkerService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CarService carService;
	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private OrderPartService orderPartService;
	@Autowired
	private OrderWorkerService orderWorkerService;

	@RequestMapping(value = "/addOrder.do", method = RequestMethod.GET)
	public String addOrder(Model model) {
		model.addAttribute("action", "add");
		model.addAttribute("cars", carService.getAllCars());
		model.addAttribute("order_statuses", orderStatusService.getAllOrderStatuses());
		return "/WEB-INF/jsp/order.jsp";
	}

	@RequestMapping(value = "/editOrder.do", method = RequestMethod.POST)
	public String editOrder(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "order_id") long orderId, Model model) {
		model.addAttribute("order", orderService.getOrderById(orderId));
		model.addAttribute("action", "edit");
		model.addAttribute("cars", carService.getAllCars());
		model.addAttribute("parts", orderPartService.getOrderPartsAsBeansByOrderId(orderId));
		model.addAttribute("workers", orderWorkerService.getOrderWorkersAsBeansByOrderId(orderId));
		model.addAttribute("order_statuses", orderStatusService.getAllOrderStatuses());
		return "/WEB-INF/jsp/order.jsp";
	}

	@RequestMapping(value = "/deleteOrder.do", method = RequestMethod.POST)
	public String deleteOrder(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "order_id") long orderId, Model model) {
		OrderInWork order = orderService.getOrderById(orderId);
		orderWorkerService.getOrderWorkersByOrderId(orderId).forEach(x -> orderWorkerService.deleteOrderWorker(x));
		orderPartService.getOrderPartsByOrderId(orderId).forEach(x -> orderPartService.deleteOrderPart(x));
		orderService.deleteOrder(order);
		return "/nav.do";
	}
}
