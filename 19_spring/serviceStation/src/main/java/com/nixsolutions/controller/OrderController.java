package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.service.CarService;
import com.nixsolutions.service.OrderService;
import com.nixsolutions.service.OrderStatusService;
import com.nixsolutions.service.OrderWorkerService;
import com.nixsolutions.service.PartOrderService;
import com.nixsolutions.service.PartService;
import com.nixsolutions.service.WorkerService;

@Controller
public class OrderController {
	// region - beans
	@Autowired
	private CarService carService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PartOrderService partOrderService;
	@Autowired
	private PartService partService;
	@Autowired
	private OrderWorkerService orderWorkerService;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private OrderStatusService orderStatusService;
	// endRegion

	@RequestMapping(value = "/admin/adminPage", method = RequestMethod.GET)
	public String loadAdminPage(Model model) {
		model.addAttribute("orders", orderService.getAllOrders());
		return "adminPage";
	}

	// region - new order
	@RequestMapping(value = "/admin/addNewOrder", method = RequestMethod.GET)
	public String navigateToAddNewPage(Model model) {
		model.addAttribute("carList", carService.getAllCar());
		return "newOrder";
	}

	@RequestMapping(value = "/admin/createNewOrder", method = RequestMethod.POST)
	public String createNewOrder(@RequestParam(value = "target", required = false) String target,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "regNumber", required = false) String regNumber, Model model) {
		orderService.createNewOrder(regNumber, description);
		model.addAttribute("orders", orderService.getAllOrders());
		return "adminPage";
	}
	// endRegion

	@RequestMapping(value = "/admin/updateOrder", method = RequestMethod.POST)
	public String updateOrder(@RequestParam(value = "order_id", required = false) String orderId, Model model) {
		model.addAttribute("order", orderService.getOrderByID(orderId));
		model.addAttribute("parts", partOrderService.getPartsByOrderId(orderId));
		model.addAttribute("allParts", partService.getAllParts());
		model.addAttribute("workers", orderWorkerService.getWorkersByOrderID(orderId));
		model.addAttribute("allWorkers", workerService.getAllWorkers());
		model.addAttribute("statuses", orderStatusService.getAllStatus());
		return "editOrder";
	}

	@RequestMapping(value = "/admin/updateOrderGeneralInfo", method = RequestMethod.POST)
	public String updateOrderGeneralInfo(@RequestParam(value = "order_status_id", required = false) String statusId,
			@RequestParam(value = "order_id", required = false) String orderId,
			@RequestParam(value = "order_description", required = false) String description, Model model) {
		orderService.changeOrderStatusByOrderID(orderId, statusId);
		orderService.updateOrderDescriptionByID(orderId, description);
		return updateOrder(orderId, model);
	}

	@RequestMapping(value = "/admin/deleteOrder", method = RequestMethod.POST)
	public String deleteOrder(@RequestParam(value = "order_id", required = false) String orderId, Model model) {
		try {
			orderService.deleteOrderByID(orderId);
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("message", "can't delete order " + orderId);
		}
		model.addAttribute("orders", orderService.getAllOrders());
		return "adminPage";
	}

	// region - add/edit/delete part to order
	@RequestMapping(value = "/admin/editPartInOrder", method = RequestMethod.POST)
	public String editPartInOrder(@RequestParam(value = "partQuant", required = false) String partQuant,
			@RequestParam(value = "order_id", required = false) String orderId,
			@RequestParam(value = "part_id", required = false) String partId, Model model) {
		if(partQuant.isEmpty())partQuant="0";
		partService.changeAmount(orderId, partId, partQuant);
		partOrderService.setPartToOrder(orderId, partId, partQuant);
		return updateOrder(orderId, model);
	}

	@RequestMapping(value = "/admin/addPartToOrder", method = RequestMethod.POST)
	public String addPartToOrder(@RequestParam(value = "part_id", required = false) String partId,
			@RequestParam(value = "order_id", required = false) String orderId,
			@RequestParam(value = "partQuant", required = false) String partQuant, Model model) {
		partService.changeAmount(partId, partQuant);
		partOrderService.setPartToOrder(orderId, partId, partQuant);
		return updateOrder(orderId, model);
	}

	@RequestMapping(value = "/admin/deletePartInOrder", method = RequestMethod.POST)
	public String deletePartInOrder(@RequestParam(value = "part_id", required = false) String partId,
			@RequestParam(value = "order_id", required = false) String orderId, Model model) {
		partOrderService.deletePartFromOrder(orderId, partId);
		return updateOrder(orderId, model);
	}
	// endRegion

	// region - add/edit/delete worker to order
	@RequestMapping(value = "/admin/addWorkerOrder", method = RequestMethod.POST)
	public String addWorkerOrder(@RequestParam(value = "worker_id", required = false) String workerId,
			@RequestParam(value = "order_id", required = false) String orderId, Model model) {
		orderWorkerService.assignWorkerToOrder(orderId, workerId);
		return updateOrder(orderId, model);
	}

	@RequestMapping(value = "/admin/updateWorkerOrder", method = RequestMethod.POST)
	public String updateWorkerOrder(@RequestParam(value = "worker_id", required = false) String workerId,
			@RequestParam(value = "order_id", required = false) String orderId,
			@RequestParam(value = "isFinished", required = false) String isFinished, Model model) {
		orderWorkerService.changeStatus(orderId, workerId, isFinished.equalsIgnoreCase("yes"));
		return updateOrder(orderId, model);

	}
	// endRegion
}
