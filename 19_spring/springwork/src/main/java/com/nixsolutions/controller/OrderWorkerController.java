package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.bean.OrderWorkerBean;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.OrderPartService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.OrderStatusService;
import com.nixsolutions.service.OrderWorkerService;
import com.nixsolutions.service.WorkerService;

@Controller
public class OrderWorkerController {

	@Autowired
	private OrderWorkerService orderWorkerService;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CarService carService;
	@Autowired
	private OrderPartService orderPartService;
	@Autowired
	private OrderStatusService orderStatusService;

	@RequestMapping(value = "/addOrderWorker.do", method = RequestMethod.GET)
	public String addOrderWorker(@ModelAttribute(value = "order_id") long orderId, Model model) {
		model.addAttribute("action", "add");
		model.addAttribute("workers", workerService.getAllWorkers());
		OrderWorkerBean owb = new OrderWorkerBean();
		owb.setOrderId(orderId);
		owb.setIsCompleted("N");
		model.addAttribute("worker", owb);
		return "/WEB-INF/jsp/orderWorker.jsp";
	}

	@RequestMapping(value = "/editOrderWorker.do", method = RequestMethod.POST)
	public String editOrderWorker(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "order_id") long orderId, @ModelAttribute(value = "worker_id") int workerId,
			Model model) {
		model.addAttribute("action", "edit");
		model.addAttribute("workers", workerService.getAllWorkers());
		model.addAttribute("worker", orderWorkerService.getOrderWorkerByIdsAsBean(orderId, workerId));
		return "/WEB-INF/jsp/orderWorker.jsp";
	}

	@RequestMapping(value = "/deleteOrderWorker.do", method = RequestMethod.POST)
	public String deleteOrderWorker(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "order_id") long orderId, @ModelAttribute(value = "worker_id") int workerId,
			Model model) {
		orderWorkerService.deleteOrderWorker(orderWorkerService.getOrderWorkerByIds(orderId, workerId));
		model.addAttribute("order", orderService.getOrderById(orderId));
		model.addAttribute("action", "edit");
		model.addAttribute("cars", carService.getAllCars());
		model.addAttribute("parts", orderPartService.getOrderPartsAsBeansByOrderId(orderId));
		model.addAttribute("workers", orderWorkerService.getOrderWorkersAsBeansByOrderId(orderId));
		model.addAttribute("order_statuses", orderStatusService.getAllOrderStatuses());
		return "/WEB-INF/jsp/order.jsp";
	}
}
