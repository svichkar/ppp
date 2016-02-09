package com.nixsolutions.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entities.AllPartsInOrder;
import com.nixsolutions.entities.AllWorkersInOrder;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderStatus;
import com.nixsolutions.entities.PartOrder;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.OrderInWorkService;
import com.nixsolutions.service.OrderStatusService;
import com.nixsolutions.service.OrderWorkerService;
import com.nixsolutions.service.PartOrderService;
import com.nixsolutions.service.PartService;

@Controller
public class OrderPartController {

	@Autowired
	private PartService partServiceImpl;
	@Autowired
	private OrderInWorkService orderInWorkServiceImpl;
	@Autowired
	private PartOrderService partOrderServiceImpl;
	@Autowired
	private OrderWorkerService orderWorkerServiceImpl;
	@Autowired
	private PartOrderService orderPartServiceImpl;
	@Autowired
	private OrderStatusService orderStatusServiceImpl;
	@Autowired
	private CarService carServiceImpl;

	@RequestMapping(value = { "/orderPartAdd", "/orderPartEdit" }, method = RequestMethod.POST)
	public String processOrderPart(@RequestParam(value = "order_id") String order_id,
			@RequestParam(value = "part_id", required = false) String part_id,
			@RequestParam(value = "amount", required = false) String amountParts,
			@RequestParam(value = "action") String action, Model model) {

		order_id = order_id != null ? order_id : "";
		part_id = part_id != null ? part_id : "";
		amountParts = amountParts != null ? amountParts : "";

		long orderId = NumberUtils.isDigits(order_id) ? Integer.parseInt(order_id) : 0;
		long partId = NumberUtils.isDigits(part_id) ? Integer.parseInt(part_id) : 0;
		long amount = NumberUtils.isDigits(amountParts) ? Integer.parseInt(amountParts) : 0;

		/// get items for select control
		List<AllWorkersInOrder> allWorkersInOrder = new ArrayList<>();
		if (orderId > 0) {
			allWorkersInOrder.addAll(orderWorkerServiceImpl.getAllWorkersInOrderById(orderId));
		}
		List<Car> cars = carServiceImpl.getAllCars();
		List<OrderStatus> allOrderStatus = orderStatusServiceImpl.getAllOrderStatus();

		PartOrder orderPart = partOrderServiceImpl.getPartOrderbyPartAndOrder(orderId, partId);
		OrderInWork selectedOrderInWork = orderInWorkServiceImpl.getOrderInWorkById(orderId);

		if (orderPart != null) {
			AllPartsInOrder allOrderPart = partOrderServiceImpl.findByPartAndOrder(orderId, partId);
			if (action.equalsIgnoreCase("Edit")) {
				model.addAttribute("title", "Edit part in order");
				model.addAttribute("order_id", orderId);
				model.addAttribute("part", allOrderPart);
				model.addAttribute("parts", partServiceImpl.getAllPart());
				model.addAttribute("action", "Edit");
				model.addAttribute("jsForPage", "editpo");
				return "/WEB-INF/jsp/editpo.jsp";
			} else if (action.equalsIgnoreCase("Delete")) {
				partOrderServiceImpl.deletePartOrder(orderPart);
				model.addAttribute("allOrderWorkers", allWorkersInOrder);
				model.addAttribute("allOrderParts", orderPartServiceImpl.getAllPartOrders(orderId));
				model.addAttribute("orderInWork", selectedOrderInWork);
				model.addAttribute("cars", cars);
				model.addAttribute("allOrderStatuses", allOrderStatus);
				model.addAttribute("title", "Order");
				return "/WEB-INF/jsp/order.jsp";
			} else if (action.equalsIgnoreCase("Save")) {
				orderPart.setPart(partServiceImpl.getPartById(partId));
				orderPart.setOrder(orderInWorkServiceImpl.getOrderInWorkById(orderId));
				orderPart.setAmount(amount);
				partOrderServiceImpl.updatePartOrder(orderPart);
				model.addAttribute("allOrderWorkers", allWorkersInOrder);
				model.addAttribute("allOrderParts", orderPartServiceImpl.getAllPartOrders(orderId));
				model.addAttribute("orderInWork", selectedOrderInWork);
				model.addAttribute("cars", cars);
				model.addAttribute("allOrderStatuses", allOrderStatus);
				model.addAttribute("title", "Order");
				return "/WEB-INF/jsp/order.jsp";
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				model.addAttribute("title", "Add part to order");
				model.addAttribute("action", "Add");
				model.addAttribute("order_id", orderId);
				model.addAttribute("parts", partServiceImpl.getAllPart());
				model.addAttribute("jsForPage", "editpo");
				return "/WEB-INF/jsp/editpo.jsp";
			} else if (action.equalsIgnoreCase("Save")) {
				PartOrder orderpartNew = new PartOrder(orderInWorkServiceImpl.getOrderInWorkById(orderId),
						partServiceImpl.getPartById(partId), amount);
				partOrderServiceImpl.addPartOrder(orderpartNew);
				model.addAttribute("allOrderWorkers", allWorkersInOrder);
				model.addAttribute("allOrderParts", orderPartServiceImpl.getAllPartOrders(orderId));
				model.addAttribute("orderInWork", selectedOrderInWork);
				model.addAttribute("cars", cars);
				model.addAttribute("allOrderStatuses", allOrderStatus);
				model.addAttribute("title", "Order");
				return "/WEB-INF/jsp/order.jsp";
			}
		}

		return "/navigation";

	}

}
