package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.bean.OrderPartBean;
import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.OrderPartService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.OrderStatusService;
import com.nixsolutions.service.OrderWorkerService;
import com.nixsolutions.service.PartService;

@Controller
public class OrderPartController {

	@Autowired
	private OrderPartService orderPartService;
	@Autowired
	private PartService partService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CarService carService;
	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private OrderWorkerService orderWorkerService;

	@RequestMapping(value = "/admin/addOrderPart.do", method = RequestMethod.GET)
	public String addOrderPart(@ModelAttribute(value = "order_id") long orderId, Model model) {
		model.addAttribute("action", "add");
		model.addAttribute("parts", partService.getAllParts());
		OrderPartBean opb = new OrderPartBean();
		opb.setOrderId(orderId);
		opb.setUsedAmount(0);
		model.addAttribute("part", opb);
		return "/WEB-INF/jsp/orderPart.jsp";
	}

	@RequestMapping(value = "/admin/editOrderPart.do", method = RequestMethod.POST)
	public String editOrderPart(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "order_id") long orderId, @ModelAttribute(value = "part_id") long partId,
			Model model) {
		model.addAttribute("action", "edit");
		model.addAttribute("parts", partService.getAllParts());
		model.addAttribute("part", orderPartService.getOrderPartByIdsAsBean(orderId, partId));
		return "/WEB-INF/jsp/orderPart.jsp";
	}

	@RequestMapping(value = "/admin/deleteOrderPart.do", method = RequestMethod.POST)
	public String deleteOrderPart(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "order_id") long orderId, @ModelAttribute(value = "part_id") long partId,
			Model model) {
		orderPartService.deleteOrderPart(orderPartService.getOrderPartByIds(orderId, partId));
		model.addAttribute("order", orderService.getOrderById(orderId));
		model.addAttribute("action", "edit");
		model.addAttribute("cars", carService.getAllCars());
		model.addAttribute("parts", orderPartService.getOrderPartsAsBeansByOrderId(orderId));
		model.addAttribute("workers", orderWorkerService.getOrderWorkersAsBeansByOrderId(orderId));
		model.addAttribute("order_statuses", orderStatusService.getAllOrderStatuses());
		return "/WEB-INF/jsp/order.jsp";
	}

	@RequestMapping(value = "/admin/orderPartPost.do", method = RequestMethod.POST)
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
