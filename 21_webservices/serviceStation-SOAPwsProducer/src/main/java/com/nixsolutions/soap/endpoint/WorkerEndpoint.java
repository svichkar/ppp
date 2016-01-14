package com.nixsolutions.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.dao.UserRoleDao;
import com.nixsolutions.dao.WorkerDao;
import com.nixsolutions.dao.WorkerSpecializationDao;
import com.nixsolutions.dao.WorkerStatusDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.soap.wsdl.CreateNewWorkerRequest;
import com.nixsolutions.soap.wsdl.CreateNewWorkerResponse;
import com.nixsolutions.soap.wsdl.UpdateWorkerRequest;
import com.nixsolutions.soap.wsdl.UpdateWorkerResponse;

@Endpoint
public class WorkerEndpoint {
	private static final String NAMESPACE_URI = "http://localhost:8080/serviceStation/soapws";

	@Autowired
	private WorkerDao workerDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private WorkerSpecializationDao workerSpecializationDao;
	@Autowired
	private WorkerStatusDao workerStatusDao;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createNewWorkerRequest")
	@ResponsePayload
	public CreateNewWorkerResponse createNewWorker(@RequestPayload CreateNewWorkerRequest request) {
		CreateNewWorkerResponse response = new CreateNewWorkerResponse();
		userDao.createNewUser(request.getWorker().getUser().getLogin(), request.getWorker().getUser().getPassword(),
				userRoleDao.getUserRole(request.getWorker().getUser().getUserRole().getUserRoleId().intValue()));
		User user = userDao.getUserByLogin(request.getWorker().getUser().getLogin());

		Worker worker = new Worker();
		worker.setLastName(request.getWorker().getLastName());
		worker.setFirstName(request.getWorker().getFirstName());
		worker.setSpecialization(workerSpecializationDao.getSpecialization(
				request.getWorker().getWorkerSpecialization().getWorkerSpecializationId().intValue()));
		worker.setWorkerStatus(workerStatusDao.getWorkerStatus("free"));

		worker.setUser(user);
		workerDao.createWorker(worker);
		response.setResult("Worker was created");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateWorkerRequest")
	@ResponsePayload
	public UpdateWorkerResponse updateWorker(@RequestPayload UpdateWorkerRequest request) {
		UpdateWorkerResponse response = new UpdateWorkerResponse();
		User user = userDao.getUserByLogin(request.getWorker().getUser().getLogin());
		user.setUserLogin(request.getWorker().getUser().getLogin());
		user.setUserPassword(request.getWorker().getUser().getPassword());
		user.setUserRole(
				userRoleDao.getUserRole(request.getWorker().getUser().getUserRole().getUserRoleId().intValue()));
		userDao.updateUser(user);
		Worker worker = workerDao.getWorkerByID(request.getWorker().getWorkerId().intValue());
		worker.setLastName(request.getWorker().getLastName());
		worker.setFirstName(request.getWorker().getFirstName());
		worker.setSpecialization(workerSpecializationDao.getSpecialization(
				request.getWorker().getWorkerSpecialization().getWorkerSpecializationId().intValue()));
		worker.setWorkerStatus(
				workerStatusDao.getWorkerStatus(request.getWorker().getWorkerStatus().getWorkerStatusName()));

		worker.setUser(user);
		workerDao.updateWorker(worker);
		response.setResult("Worker was created");
		return response;
	}
}
