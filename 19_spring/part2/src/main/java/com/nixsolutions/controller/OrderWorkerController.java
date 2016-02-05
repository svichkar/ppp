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
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.OrderInWorkService;
import com.nixsolutions.service.OrderStatusService;
import com.nixsolutions.service.OrderWorkerService;
import com.nixsolutions.service.PartOrderService;
import com.nixsolutions.service.WorkerService;

@Controller
public class OrderWorkerController {

	@Autowired
	private OrderWorkerService orderWorkerServiceImpl;
	@Autowired
	private WorkerService workerServiceImpl;
	@Autowired
	private OrderInWorkService orderInWorkServiceImpl;
	@Autowired
	private PartOrderService orderPartServiceImpl;
	@Autowired
	private OrderStatusService orderStatusServiceImpl;
	@Autowired
	private CarService carServiceImpl;

	@RequestMapping(value = { "/orderWorkerAdd", "/orderWorkerEdit" }, method = RequestMethod.POST)
	public String processOrderWorker(@RequestParam(value = "order_id", required = false) String order_id,
			@RequestParam(value = "worker_id", required = false) String worker_id,
			@RequestParam(value = "completed_value", required = false) String completed_value,
			@RequestParam(value = "action") String action, Model model) {

		long orderId = NumberUtils.isDigits(order_id) ? Integer.parseInt(order_id) : 0;
		long workerId = NumberUtils.isDigits(worker_id) ? Integer.parseInt(worker_id) : 0;
		boolean completed = completed_value != null ? Boolean.parseBoolean(completed_value) : false;
		OrderWorker orderWorker = orderWorkerServiceImpl.getOrderWorkerByOrderAndWorker(orderId, workerId);

		/// get items for select control
		List<AllPartsInOrder> allPartsInOrder = new ArrayList<>();
		if (orderId > 0) {
			allPartsInOrder.addAll(orderPartServiceImpl.getAllPartOrders(orderId));
		}
		List<Car> cars = carServiceImpl.getAllCars();
		List<OrderStatus> allOrderStatus = orderStatusServiceImpl.getAllOrderStatus();

		OrderInWork selectedOrderInWork = orderInWorkServiceImpl.getOrderInWorkById(orderId);
		if (orderWorker != null) {
			AllWorkersInOrder allorderworker = orderWorkerServiceImpl.getAllWorkersInOrderByOrderAndWorker(orderId,
					workerId);
			if (action.equalsIgnoreCase("Edit")) {
				model.addAttribute("order_id", orderId);
				model.addAttribute("worker", allorderworker);
				model.addAttribute("workers", workerServiceImpl.getAllWorkers());
				model.addAttribute("action", "Edit");
				model.addAttribute("title", "Edit worker in order");
				return "/WEB-INF/jsp/editwo.jsp";
			} else if (action.equalsIgnoreCase("Delete")) {
				orderWorkerServiceImpl.deleteOrderWorker(orderWorker);
				model.addAttribute("allOrderWorkers", orderWorkerServiceImpl.getAllWorkersInOrderById(orderId));
				model.addAttribute("allOrderParts", allPartsInOrder);
				model.addAttribute("orderInWork", selectedOrderInWork);
				model.addAttribute("cars", cars);
				model.addAttribute("allOrderStatuses", allOrderStatus);
				model.addAttribute("title", "Order");
				return "/WEB-INF/jsp/order.jsp";
			} else if (action.equalsIgnoreCase("Save")) {
				orderWorker.setWorker(workerServiceImpl.getWorkerById(workerId));
				orderWorker.setOrderInWork(orderInWorkServiceImpl.getOrderInWorkById(orderId));
				orderWorker.setCompleted(completed);
				orderWorkerServiceImpl.updateOrderWorker(orderWorker);
				model.addAttribute("allOrderWorkers", orderWorkerServiceImpl.getAllWorkersInOrderById(orderId));
				model.addAttribute("allOrderParts", allPartsInOrder);
				model.addAttribute("orderInWork", selectedOrderInWork);
				model.addAttribute("cars", cars);
				model.addAttribute("allOrderStatuses", allOrderStatus);
				model.addAttribute("title", "Order");
				return "/WEB-INF/jsp/order.jsp";
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				model.addAttribute("action", "Add");
				model.addAttribute("order_id", orderId);
				model.addAttribute("workers", workerServiceImpl.getAllWorkers());
				model.addAttribute("title", "Add worker in order");
				return "/WEB-INF/jsp/editwo.jsp";
			} else if (action.equalsIgnoreCase("Save")) {
				OrderWorker orderworkerNew = new OrderWorker(workerServiceImpl.getWorkerById(workerId),
						orderInWorkServiceImpl.getOrderInWorkById(orderId), completed);
				orderWorkerServiceImpl.addOrderWorker(orderworkerNew);
				model.addAttribute("allOrderWorkers", orderWorkerServiceImpl.getAllWorkersInOrderById(orderId));
				model.addAttribute("allOrderParts", allPartsInOrder);
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
