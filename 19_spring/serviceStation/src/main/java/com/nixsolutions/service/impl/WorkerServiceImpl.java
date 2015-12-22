/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.WorkerDao;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.service.WorkerService;

/**
 * @author mixeyes
 *
 */
@Service
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDao workerDao;

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
	public boolean deleteWorker(String last_name, String first_name) {
		return workerDao.deleteWorker(last_name, first_name);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.WorkerService#getWorker(java.lang.Integer)
	 */
	@Override
	public Worker getWorker(Integer user_id) {
		return workerDao.getWorker(user_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerService#getWorkerByID(java.lang.Integer)
	 */
	@Override
	public Worker getWorkerByID(Integer worker_id) {
		return workerDao.getWorkerByID(worker_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.WorkerService#getWorkerByID(java.lang.Integer)
	 */
	@Override
	public Worker getWorkerByID(String worker_id) {
		return getWorkerByID(Integer.decode(worker_id));
	}

}
