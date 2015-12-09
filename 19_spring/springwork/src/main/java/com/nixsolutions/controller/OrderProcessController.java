package com.nixsolutions.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.OrderStatusService;

@Controller
public class OrderProcessController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private CarService carService;

	@RequestMapping(value = "/orderPost.do", method = RequestMethod.POST)
	public String processOrder(@ModelAttribute(value = "id") String orderId,
			@ModelAttribute(value = "order_status_id") String orderStatusId,
			@ModelAttribute(value = "order_description") String orderDescription,
			@ModelAttribute(value = "car_id") String carId,
			@ModelAttribute(value = "timestamp_started") String timestampStart,
			@ModelAttribute(value = "timestamp_finished") String timestampFinish, Model model) throws ParseException {
		OrderInWork order = orderService.getOrderById(orderId.equals("") ? 0L : Long.parseLong(orderId));
		if (order == null) {
			order = new OrderInWork(orderStatusService.getOrderStatusById(Integer.parseInt(orderStatusId)),
					orderDescription, carService.getCarById(Integer.parseInt(carId)),
					new Timestamp(new Date().getTime()), null);
			orderService.addOrder(order);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			order.setOrderStatus(orderStatusService.getOrderStatusById(Integer.parseInt(orderStatusId)));
			order.setDescription(orderDescription);
			order.setCar(carService.getCarById(Integer.parseInt(carId)));
			order.setTimestampStart(
					timestampStart.equals("") ? null : new Timestamp(sdf.parse(timestampStart).getTime()));
			order.setTimestampFinish(
					timestampFinish.equals("") ? null : new Timestamp(sdf.parse(timestampFinish).getTime()));
			orderService.updateOrder(order);
		}
		model.addAttribute("target", "Orders");
		return "/nav.do";
	}
}
