package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.entities.Worker;
import com.nixsolutions.service.StatusService;
import com.nixsolutions.service.WorkerService;
import com.nixsolutions.service.WorkerSpecificationService;

@Controller
public class WorkerController {

	@Autowired
	private WorkerService workerServiceImpl;
	@Autowired
	private StatusService statusServiceImpl;
	@Autowired
	private WorkerSpecificationService workerSpecificationServiceImpl;

	@RequestMapping(value = { "/workerAdd", "/workerEdit" }, method = RequestMethod.POST)
	public String processWorker(@RequestParam(value = "worker_id", required = false) String worker_id,
			@RequestParam(value = "f_name", required = false) String f_name,
			@RequestParam(value = "l_name", required = false) String l_name,
			@RequestParam(value = "status_id", required = false) String status_id,
			@RequestParam(value = "spec_id", required = false) String spec_id,
			@RequestParam(value = "action") String action, Model model) {
		/// parse id
		int workerId = NumberUtils.isDigits(worker_id) ? Integer.parseInt(worker_id) : 0;
		int specId = NumberUtils.isDigits(spec_id) ? Integer.parseInt(spec_id) : 0;
		int statusId = NumberUtils.isDigits(status_id) ? Integer.parseInt(status_id) : 0;
		if (workerId > 0) {
			Worker worker = workerServiceImpl.getWorkerById(workerId);
			if (action.equalsIgnoreCase("Edit")) {
				model.addAttribute("worker", worker);
				model.addAttribute("statuses", statusServiceImpl.getAllStatuses());
				model.addAttribute("specifications", workerSpecificationServiceImpl.getAllWorkerSpecifications());
				model.addAttribute("title", "Worker");
				return "/WEB-INF/jsp/worker.jsp";
			} else if (action.equalsIgnoreCase("Delete")) {
				workerServiceImpl.deleteWorker(worker);
				model.addAttribute("title", "Workers");
				model.addAttribute("destination", "Workers");
				return "/navigation";
			} else if (action.equalsIgnoreCase("Save")) {
				worker.setF_name(f_name);
				worker.setL_name(l_name);
				worker.setSpec(workerSpecificationServiceImpl.getWorkerSpecificationByid(specId));
				worker.setStatus(statusServiceImpl.getStatusById(statusId));
				workerServiceImpl.updateWorker(worker);
				model.addAttribute("title", "Workers");
				model.addAttribute("destination", "Workers");
				return "/navigation";
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				model.addAttribute("title", "Add worker");
				model.addAttribute("statuses", statusServiceImpl.getAllStatuses());
				model.addAttribute("specifications", workerSpecificationServiceImpl.getAllWorkerSpecifications());
				return "/WEB-INF/jsp/worker.jsp";
			} else if (action.equalsIgnoreCase("Save")) {
				Worker worker = new Worker(f_name, l_name,
						workerSpecificationServiceImpl.getWorkerSpecificationByid(specId),
						statusServiceImpl.getStatusById(statusId));
				workerServiceImpl.addWorker(worker);
				model.addAttribute("destination", "Workers");
				model.addAttribute("title", "Workers");
				return "/navigation";
			}

		}

		model.addAttribute("destination", "Workers");
		model.addAttribute("title", "Workers");
		return "/navigation";

	}
}
