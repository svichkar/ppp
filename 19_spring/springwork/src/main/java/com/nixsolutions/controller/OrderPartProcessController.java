package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.OrderPartService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.OrderStatusService;
import com.nixsolutions.service.OrderWorkerService;
import com.nixsolutions.service.PartService;

@Controller
public class OrderPartProcessController {

	@Autowired
	private OrderPartService orderPartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PartService partService;
	@Autowired
	private OrderWorkerService orderWorkerService;
	@Autowired
	private CarService carService;
	@Autowired
	private OrderStatusService orderStatusService;

	@RequestMapping(value = "/orderPartPost.do", method = RequestMethod.POST)
	public String processOrderPart(@ModelAttribute(value = "order_id") String orderId,
			@ModelAttribute(value = "part_id") String partId, @ModelAttribute(value = "used_amount") String usedAmount,
			Model model) {
		Long orderIdLong = orderId.equals("") ? 0 : Long.parseLong(orderId);
		Long partIdLong = partId.equals("") ? 0 : Long.parseLong(partId);
		Long usedAmountLong = usedAmount.equals("") ? 0 : Long.parseLong(usedAmount);
		OrderPart orderPart = orderPartService.getOrderPartByIds(orderIdLong, partIdLong);
		if (orderPart == null) {
			orderPart = new OrderPart(orderService.getOrderById(orderIdLong), partService.getPartById(partIdLong),
					usedAmountLong);
			orderPartService.addOrderPart(orderPart);
		} else {
			orderPart.setUsedAmount(usedAmountLong);
			orderPartService.updateOrderPart(orderPart);
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
