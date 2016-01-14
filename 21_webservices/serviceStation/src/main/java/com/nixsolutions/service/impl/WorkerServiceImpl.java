/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.WorkerDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.entity.WorkerSpecialization;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.WorkerService;
import com.nixsolutions.service.WorkerStatusService;

/**
 * @author mixeyes
 *
 */
@Service
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDao workerDao;
	@Autowired
	private UserService userService;
	@Autowired
	private WorkerStatusService workerStatusService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.WorkerService#getAllWorkers()
	 */
	@Override
	public List<Worker> getAllWorkers() {
		return workerDao.getAllWorkers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerService#getAllWorkersBySpecialization(java
	 * .lang.String)
	 */
	@Override
	public List<Worker> getAllWorkersBySpecialization(String specialization) {
		return workerDao.getAllWorkersBySpecialization(specialization);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.WorkerService#getWorker(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Worker getWorker(String last_name, String first_name) {
		return workerDao.getWorker(last_name, first_name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerService#getWorkerStatus(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String getWorkerStatus(String lastName, String first_name) {
		return workerDao.getWorkerStatus(lastName, first_name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerService#createWorker(com.nixsolutions.
	 * entity.Worker)
	 */
	@Override
	public void createWorker(Worker worker) {
		workerDao.createWorker(worker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerService#updateWorker(com.nixsolutions.
	 * entity.Worker)
	 */
	@Override
	public void updateWorker(Worker worker) {
		workerDao.updateWorker(worker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerService#deleteWorker(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean deleteWorker(String lastName, String firstName) {
		return workerDao.deleteWorker(lastName, firstName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerService#updateWorker(com.nixsolutions.
	 * entity.Worker)
	 */
	@Override
	public void deleteWorker(Worker worker) {
		workerDao.deleteWorker(worker);
		User user = worker.getUser();
		userService.deleteUser(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.WorkerService#getWorker(java.lang.Integer)
	 */
	@Override
	public Worker getWorker(Integer userId) {
		return workerDao.getWorker(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerService#getWorkerByID(java.lang.Integer)
	 */
	@Override
	public Worker getWorkerByID(Integer workerId) {
		return workerDao.getWorkerByID(workerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerService#getWorkerByID(java.lang.Integer)
	 */
	@Override
	public Worker getWorkerByID(String workerId) {
		return getWorkerByID(Integer.decode(workerId));
	}

	@Override
	public void createWorker(String lastName, String firstName, String userLogin, WorkerSpecialization spec) {
		Worker worker = new Worker();
		worker.setFirstName(firstName);
		worker.setLastName(lastName);
		worker.setSpecialization(spec);
		worker.setUser(userService.getUserByLogin(userLogin));
		worker.setWorkerStatus(workerStatusService.getWorkerStatus("free"));
		createWorker(worker);
	}

	@Override
	public void updateWorker(String workerId, User userByLogin, String lastName, String firstName,
			WorkerSpecialization specialization) {
		Worker worker = getWorkerByID(workerId);
		worker.setFirstName(firstName);
		worker.setLastName(lastName);
		worker.setSpecialization(specialization);
		worker.setUser(userByLogin);
		updateWorker(worker);
	}

}
