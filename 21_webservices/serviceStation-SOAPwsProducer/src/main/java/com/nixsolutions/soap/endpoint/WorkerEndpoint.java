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
	private static final String NAMESPACE_URI = "http://localhost:8080/wsSoapSTO/soapws";

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
				userRoleDao.getUserRole(Role
						.getRoleBySpecializationId(
								request.getWorker().getWorkerSpecialization().getWorkerSpecializationId().intValue())
						.toString()));
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
		User user = userDao.getUserByID(request.getWorker().getUser().getUserId().intValue());
		user.setUserLogin(request.getWorker().getUser().getLogin());
		user.setUserPassword(request.getWorker().getUser().getPassword());
		user.setUserRole(
				userRoleDao.getUserRole(Role
						.getRoleBySpecializationId(
								request.getWorker().getWorkerSpecialization().getWorkerSpecializationId().intValue())
						.toString()));
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

	public enum Role {
		ROLE_MANAGER(6), ROLE_STOREKEEPER(7), ROLE_WORKER(3), ROLE_CUSTOMER(0);

		private int specializationId;

		private void setIntValue(int specializationId) {
			this.specializationId = specializationId;
		}

		public int getIntValue() {
			return specializationId;
		}

		private Role(int specializationId) {
			setIntValue(specializationId);
		}

		public static Role getRoleBySpecializationId(int specializationId) {
			switch (specializationId) {
			case 0:
				return Role.ROLE_CUSTOMER;
			case 6:
				return Role.ROLE_MANAGER;
			case 7:
				return Role.ROLE_STOREKEEPER;
			default:
				return Role.ROLE_WORKER;
			}
		}
	}
}
