package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.service.SpecializationService;
import com.nixsolutions.service.WorkerService;
import com.nixsolutions.service.wsdl.client.WorkerServiceClient;

@Controller
public class WorkerController {
	@Autowired
	private WorkerService workerService;
	@Autowired
	private SpecializationService specializationService;
	@Autowired
	ApplicationContext applicationContext;

	@RequestMapping(value = "/admin/workerPage", method = RequestMethod.GET)
	public String loadWorkerPage(Model model) {
		model.addAttribute("workerList", workerService.getAllWorkers());
		return "workerPage";
	}

	@RequestMapping(value = "/admin/addNewWorker", method = RequestMethod.GET)
	public String addNewWorker(Model model) {
		model.addAttribute("specs", specializationService.getAllSpecialization());
		return "newWorker";
	}

	@RequestMapping(value = "/admin/createNewWorker", method = RequestMethod.POST)
	public String createNewWorker(@RequestParam(value = "last_name", required = false) String lastName,
			@RequestParam(value = "first_name", required = false) String firstName,
			@RequestParam(value = "specialization_id", required = false) String specializationId,
			@RequestParam(value = "userLogin", required = false) String userLogin,
			@RequestParam(value = "userPassword", required = false) String userPassword,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {

		WorkerServiceClient serviceClient = applicationContext.getBean(WorkerServiceClient.class);
		serviceClient.createNewWorkerResponse(userLogin, userPassword, lastName, firstName, specializationId);
	
/*		userService.createNewUser(userLogin, userPassword, specializationId);
		workerService.createWorker(lastName, firstName, userLogin,
				specializationService.getSpecialization(specializationId));
*/
		model.addAttribute("workerList", workerService.getAllWorkers());
		return "workerPage";
	}

	@RequestMapping(value = "/admin/updateExistingWorker", method = RequestMethod.POST)
	public String updateExistingWorker(@RequestParam(value = "worker_id", required = false) String worker_id,
			Model model) {
		model.addAttribute("specs", specializationService.getAllSpecialization());
		model.addAttribute("worker", workerService.getWorkerByID(worker_id));
		return "editWorker";
	}

	@RequestMapping(value = "/admin/updateWorker", method = RequestMethod.POST)
	public String updateWorker(@RequestParam(value = "last_name", required = false) String lastName,
			@RequestParam(value = "first_name", required = false) String firstName,
			@RequestParam(value = "specialization_id", required = false) String specializationId,
			@RequestParam(value = "user_login", required = false) String userLogin,
			@RequestParam(value = "user_password", required = false) String userPassword,
			@RequestParam(value = "homePage", required = false) String homePage,
			@RequestParam(value = "worker_id", required = false) String workerId,
			@RequestParam(value = "user_id", required = false) String userId, Model model) {

		WorkerServiceClient serviceClient = applicationContext.getBean(WorkerServiceClient.class);
		serviceClient.updateWorkerResponse(userId, userLogin, userPassword, workerId, lastName, firstName, specializationId);

/*		userService.updateUser(userId, userLogin, userPassword);
		workerService.updateWorker(workerId, userService.getUserByLogin(userLogin), lastName, firstName,
				specializationService.getSpecialization(specializationId));
*/
		model.addAttribute("workerList", workerService.getAllWorkers());
		return "workerPage";

	}

	@RequestMapping(value = "/admin/deleteWorker", method = RequestMethod.POST)
	public String deleteWorker(@RequestParam(value = "worker_id", required = false) String worker_id,
			@RequestParam(value = "user_id", required = false) String user_id, Model model) {

		workerService.deleteWorker(workerService.getWorkerByID(worker_id));
		model.addAttribute("workerList", workerService.getAllWorkers());
		return "workerPage";
	}
}
