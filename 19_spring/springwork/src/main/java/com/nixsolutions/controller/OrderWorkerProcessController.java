package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.IsCompletedValue;
import com.nixsolutions.hibernate.entity.OrderWorker;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.OrderPartService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.OrderStatusService;
import com.nixsolutions.service.OrderWorkerService;
import com.nixsolutions.service.WorkerService;

@Controller
public class OrderWorkerProcessController {

	@Autowired
	private OrderWorkerService orderWorkerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private CarService carService;
	@Autowired
	private OrderPartService orderPartService;
	@Autowired
	private OrderStatusService orderStatusService;

	@RequestMapping(value = "/orderWorkerPost.do", method = RequestMethod.POST)
	public String processOrderWorker(@ModelAttribute(value = "order_id") String orderId,
			@ModelAttribute(value = "worker_id") String workerId,
			@ModelAttribute(value = "is_completed") String isCompleted, Model model) {
		Long orderIdLong = orderId.equals("") ? 0 : Long.parseLong(orderId);
		Integer workerIdInt = workerId.equals("") ? 0 : Integer.parseInt(workerId);
		OrderWorker orderWorker = orderWorkerService.getOrderWorkerByIds(orderIdLong, workerIdInt);
		if (orderWorker == null) {
			orderWorker = new OrderWorker(orderService.getOrderById(orderIdLong),
					workerService.getWorkerById(workerIdInt), IsCompletedValue.valueOf(isCompleted));
			orderWorkerService.addOrderWorker(orderWorker);
		} else {
			orderWorker.setIsCompleted(IsCompletedValue.valueOf(isCompleted));
			orderWorkerService.updateOrderWorker(orderWorker);
		}
		model.addAttribute("order", orderService.getOrderById(orderIdLong));
		model.addAttribute("action", "edit");
		model.addAttribute("cars", carService.getAllCars());
		model.addAttribute("parts", orderPartService.getOrderPartsAsBeansByOrderId(orderIdLong));
		model.addAttribute("workers", orderWorkerService.getOrderWorkersAsBeansByOrderId(orderIdLong));
		model.addAttribute("order_statuses", orderStatusService.getAllOrderStatuses());
		return "/WEB-INF/jsp/order.jsp";
	}
}
