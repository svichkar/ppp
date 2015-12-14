package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.hibernate.entity.Worker;
import com.nixsolutions.service.OrderWorkerService;
import com.nixsolutions.service.StatusService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.WorkerService;
import com.nixsolutions.service.WorkerSpecializationService;

@Controller
public class WorkerController {

	@Autowired
	private StatusService statusService;
	@Autowired
	private WorkerSpecializationService specService;
	@Autowired
	private UserService userService;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private OrderWorkerService orderWorkerService;

	@RequestMapping(value = "/admin/addWorker.do", method = RequestMethod.GET)
	public String addWorker(Model model) {
		model.addAttribute("action", "add");
		model.addAttribute("statuses", statusService.getAllStatuses());
		model.addAttribute("specs", specService.getAllSpecs());
		model.addAttribute("users", userService.getUsersWithRoleWorker());
		return "/WEB-INF/jsp/worker.jsp";
	}

	@RequestMapping(value = "/admin/editWorker.do", method = RequestMethod.POST)
	public String editWorker(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "worker_id") int workerId, Model model) {
		model.addAttribute("worker", workerService.getWorkerById(workerId));
		model.addAttribute("action", "edit");
		model.addAttribute("statuses", statusService.getAllStatuses());
		model.addAttribute("specs", specService.getAllSpecs());
		model.addAttribute("users", userService.getUsersWithRoleWorker());
		return "/WEB-INF/jsp/worker.jsp";
	}

	@RequestMapping(value = "/admin/deleteWorker.do", method = RequestMethod.POST)
	public String deleteWorker(@ModelAttribute(value = "action") String action,
			@ModelAttribute(value = "worker_id") int workerId, Model model) {
		Worker worker = workerService.getWorkerById(workerId);
		orderWorkerService.getOrderWorkersByWorker(worker).forEach(x -> orderWorkerService.deleteOrderWorker(x));
		workerService.deleteWorker(worker);
		return "/nav.do";
	}

	@RequestMapping(value = "/admin/workerPost.do", method = RequestMethod.POST)
	public String processWorker(@ModelAttribute(value = "id") String workerId,
			@ModelAttribute(value = "first_name") String firstName,
			@ModelAttribute(value = "last_name") String lastName, @ModelAttribute(value = "status_id") String statusId,
			@ModelAttribute(value = "specialization_id") String specId,
			@ModelAttribute(value = "user_id") String userId, Model model) {
		Worker worker = workerService.getWorkerById(workerId.equals("") ? 0 : Integer.parseInt(workerId));
		if (worker == null) {
			worker = new Worker(firstName, lastName, specService.getSpecById(Integer.parseInt(specId)),
					statusService.getStatusById(Integer.parseInt(statusId)),
					userService.getUserById(Integer.parseInt(userId)));
			workerService.createWorker(worker);
		} else {
			worker.setFirstName(firstName);
			worker.setLastName(lastName);
			worker.setWorkerSpecialization(specService.getSpecById(Integer.parseInt(specId)));
			worker.setStatus(statusService.getStatusById(Integer.parseInt(statusId)));
			worker.setUser(userService.getUserById(Integer.parseInt(userId)));
			workerService.updateWorker(worker);
		}
		model.addAttribute("target", "Workers");
		return "/nav.do";
	}
}
