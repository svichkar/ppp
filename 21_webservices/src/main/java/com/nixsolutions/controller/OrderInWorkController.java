package com.nixsolutions.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.dto.AllPartsInOrder;
import com.nixsolutions.dto.AllWorkersInOrder;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderStatus;
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.OrderInWorkService;
import com.nixsolutions.service.OrderStatusService;
import com.nixsolutions.service.OrderWorkerService;
import com.nixsolutions.service.PartOrderService;

@Controller
public class OrderInWorkController {

	@Autowired
	private OrderWorkerService orderWorkerServiceImpl;
	@Autowired
	private PartOrderService orderPartServiceImpl;
	@Autowired
	private OrderInWorkService orderInWorkServiceImpl;
	@Autowired
	private OrderStatusService orderStatusServiceImpl;
	@Autowired
	private CarService carServiceImpl;

	@RequestMapping(value = { "/orderAdd", "/orderEdit" }, method = RequestMethod.POST)
	public String processOrderInWork(@RequestParam(value = "order_status_id", required = false) String order_status_id,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "car_id", required = false) String car_id,
			@RequestParam(value = "order_id", required = false) String order_id,
			@RequestParam(value = "datetime_start", required = false) String datetime_start,
			@RequestParam(value = "datetime_end", required = false) String datetime_end,
			@RequestParam(value = "action") String action, Model model) {
		Date dateNow = new Date();
		Timestamp datetimeStart = isStringValidDate(datetime_start) ? Timestamp.valueOf(datetime_start)
				: new Timestamp(dateNow.getTime());
		Timestamp datetimeEnd = isStringValidDate(datetime_end) ? Timestamp.valueOf(datetime_end) : null;
		int orderstatusId = NumberUtils.isDigits(order_status_id) ? Integer.parseInt(order_status_id) : 0;
		int orderId = NumberUtils.isDigits(order_id) ? Integer.parseInt(order_id) : 0;
		int carId = NumberUtils.isDigits(car_id) ? Integer.parseInt(car_id) : 0;

		/// get items for select control
		List<AllWorkersInOrder> allWorkersInOrder = new ArrayList<>();
		List<AllPartsInOrder> allPartsInOrder = new ArrayList<>();
		if (orderId > 0) {
			allWorkersInOrder.addAll(orderWorkerServiceImpl.getAllWorkersInOrderById(orderId));
			allPartsInOrder.addAll(orderPartServiceImpl.getAllPartOrders(orderId));
		}
		List<Car> cars = carServiceImpl.getAllCars();
		List<OrderStatus> allOrderStatus = orderStatusServiceImpl.getAllOrderStatus();

		if (orderId > 0) {
			OrderInWork selectedOrderInWork = orderInWorkServiceImpl.getOrderInWorkById(orderId);
			if (action.equalsIgnoreCase("Edit")) {
				model.addAttribute("allOrderWorkers", allWorkersInOrder);
				model.addAttribute("allOrderParts", allPartsInOrder);
				model.addAttribute("orderInWork", selectedOrderInWork);
				model.addAttribute("cars", cars);
				model.addAttribute("allOrderStatuses", allOrderStatus);
				model.addAttribute("title", "Order");
				model.addAttribute("jsForPage", "order");
				return "order";
			} else if (action.equalsIgnoreCase("Delete")) {
				orderInWorkServiceImpl.deleteOrderInWork(selectedOrderInWork);
				model.addAttribute("oiwcs", orderInWorkServiceImpl.getAllOrderInWorkCarStatus());
				model.addAttribute("title", "Orders");
				return "orders";
			} else if (action.equalsIgnoreCase("Save")) {
				selectedOrderInWork.setCar(carServiceImpl.getCarById(carId));
				selectedOrderInWork.setOrderStatus(orderStatusServiceImpl.getOrderStatusById(orderstatusId));
				selectedOrderInWork.setDescription(description);
				selectedOrderInWork.setDatetimeStart(datetimeStart);
				selectedOrderInWork.setDatetimeEnd(datetimeEnd);
				selectedOrderInWork.setDescription(description);
				orderInWorkServiceImpl.updateOrderInWork(selectedOrderInWork);
				model.addAttribute("oiwcs", orderInWorkServiceImpl.getAllOrderInWorkCarStatus());
				model.addAttribute("title", "Orders");
				return "orders";
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				model.addAttribute("allOrderParts", allPartsInOrder);
				model.addAttribute("cars", cars);
				model.addAttribute("allOrderStatuses", allOrderStatus);
				model.addAttribute("title", "Order");
				model.addAttribute("jsForPage", "order");
				return "order";
			} else if (action.equalsIgnoreCase("Save")) {
				OrderInWork orderinWorkAdded = new OrderInWork(orderStatusServiceImpl.getOrderStatusById(orderstatusId),
						description, carServiceImpl.getCarById(carId), datetimeStart, datetimeEnd);
				orderInWorkServiceImpl.addOrderInWork(orderinWorkAdded);
				model.addAttribute("oiwcs", orderInWorkServiceImpl.getAllOrderInWorkCarStatus());
				model.addAttribute("title", "Orders");
				return "orders";
			}
		}
		model.addAttribute("oiwcs", orderInWorkServiceImpl.getAllOrderInWorkCarStatus());
		model.addAttribute("title", "Orders");
		return "orders";

	}

	private boolean isStringValidDate(String date) {
		boolean valid = false;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			format.parse(date);
			valid = true;
		} catch (Exception ex) {
			valid = false;
		}
		return valid;
	}
}
