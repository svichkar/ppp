package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.entity.WorkerSpecialization;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.SpecializationService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.WorkerService;
import com.nixsolutions.service.WorkerStatusService;

@Controller
public class WorkerController {
	@Autowired
	private WorkerService workerService;
	@Autowired
	private RoleService userRoleService;
	@Autowired
	private UserService userService;
	@Autowired
	private SpecializationService specializationService;
	@Autowired
	private WorkerStatusService workerStatusService;

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
	public String createNewWorker(@RequestParam(value = "last_name", required = false) String last_name,
			@RequestParam(value = "first_name", required = false) String first_name,
			@RequestParam(value = "specialization_id", required = false) String specializationId,
			@RequestParam(value = "userLogin", required = false) String userLogin,
			@RequestParam(value = "userPassword", required = false) String userPassword,
			@RequestParam(value = "homePage", required = false) String homePage, Model model) {
		if (specializationId.equalsIgnoreCase("7"))
			userService.createNewUser(userLogin, userPassword, userRoleService.getUserRole("ROLE_STOREKEEPER"));
		else if (specializationId.equalsIgnoreCase("6"))
			userService.createNewUser(userLogin, userPassword, userRoleService.getUserRole("ROLE_MANAGER"));
		else
			userService.createNewUser(userLogin, userPassword, userRoleService.getUserRole("ROLE_WORKER"));
		WorkerSpecialization spec = specializationService.getSpecialization(specializationId);
		Worker worker = new Worker();
		worker.setFirst_name(first_name);
		worker.setLast_name(last_name);
		worker.setSpecialization(spec);
		worker.setUser(userService.getUserByLogin(userLogin));
		worker.setWorker_status(workerStatusService.getWorkerStatus("free"));
		workerService.createWorker(worker);
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
	public String updateWorker(@RequestParam(value = "last_name", required = false) String last_name,
			@RequestParam(value = "first_name", required = false) String first_name,
			@RequestParam(value = "specialization_id", required = false) String specializationId,
			@RequestParam(value = "user_login", required = false) String userLogin,
			@RequestParam(value = "user_password", required = false) String userPassword,
			@RequestParam(value = "homePage", required = false) String homePage,
			@RequestParam(value = "worker_id", required = false) String worker_id,
			@RequestParam(value = "user_id", required = false) String user_id, Model model) {
		User user = userService.getUserByID(user_id);
		user.setUser_login(userLogin);
		user.setUser_password(userPassword);
		userService.updateUser(user);
		WorkerSpecialization spec = specializationService.getSpecialization(specializationId);
		Worker worker = workerService.getWorkerByID(worker_id);
		worker.setFirst_name(first_name);
		worker.setLast_name(last_name);
		worker.setSpecialization(spec);
		worker.setUser(userService.getUserByLogin(userLogin));
		worker.setWorker_status(workerStatusService.getWorkerStatus("free"));
		workerService.updateWorker(worker);
		model.addAttribute("workerList", workerService.getAllWorkers());
		return "workerPage";

	}

	@RequestMapping(value = "/admin/deleteWorker", method = RequestMethod.POST)
	public String deleteWorker(@RequestParam(value = "worker_id", required = false) String worker_id,
			@RequestParam(value = "user_id", required = false) String user_id, Model model) {
		Worker worker = workerService.getWorkerByID(worker_id);
		workerService.deleteWorker(worker);
		User user = worker.getUser();
		userService.deleteUser(user);
		model.addAttribute("workerList", workerService.getAllWorkers());
		return "workerPage";
	}
}
