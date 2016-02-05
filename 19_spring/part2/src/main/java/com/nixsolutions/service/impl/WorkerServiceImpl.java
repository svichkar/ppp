package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.entities.Worker;
import com.nixsolutions.entities.WorkerStatusSpecification;
import com.nixsolutions.service.WorkerService;

@Service
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDAO workerDaoImpl;

	@Override
	public List<Worker> getAllWorkers() {
		return workerDaoImpl.getAll();
	}

	@Override
	public Worker getWorkerById(long id) {
		return workerDaoImpl.findByPK(id);
	}

	@Override
	public void addWorker(Worker worker) {
		workerDaoImpl.create(worker);
	}

	@Override
	public void updateWorker(Worker worker) {
		workerDaoImpl.update(worker);
	}

	@Override
	public void deleteWorker(Worker worker) {
		workerDaoImpl.delete(worker);

	}

	@Override
	public WorkerStatusSpecification getWorkerStatusSpecificationByPK(long workerId) {
		WorkerStatusSpecification workerStatusSpecification = null;
		Worker worker = workerDaoImpl.findByPK(workerId);
		if (worker != null) {
			workerStatusSpecification = new WorkerStatusSpecification();
			workerStatusSpecification.setF_name(worker.getF_name());
			workerStatusSpecification.setL_name(worker.getL_name());
			workerStatusSpecification.setId(worker.getWorkerId());
			workerStatusSpecification.setSpec_id(worker.getSpec().getSpecId());
			workerStatusSpecification.setSpec_name(worker.getSpec().getSpec_name());
			workerStatusSpecification.setStatus_id(worker.getStatus().getStatusId());
			workerStatusSpecification.setStatus_name(worker.getStatus().getStatus_name());
		}
		return workerStatusSpecification;
	}

	@Override
	public List<WorkerStatusSpecification> getAllWorkerStatusSpecification() {
		List<WorkerStatusSpecification> workerStatusSpecifications = new ArrayList<>();
		List<Worker> workers = workerDaoImpl.getAll();
		for (Worker worker : workers) {
			WorkerStatusSpecification workerStatusSpecification = new WorkerStatusSpecification();
			workerStatusSpecification.setF_name(worker.getF_name());
			workerStatusSpecification.setL_name(worker.getL_name());
			workerStatusSpecification.setId(worker.getWorkerId());
			workerStatusSpecification.setSpec_id(worker.getSpec().getSpecId());
			workerStatusSpecification.setSpec_name(worker.getSpec().getSpec_name());
			workerStatusSpecification.setStatus_id(worker.getStatus().getStatusId());
			workerStatusSpecification.setStatus_name(worker.getStatus().getStatus_name());
			workerStatusSpecifications.add(workerStatusSpecification);
		}
		return workerStatusSpecifications;

	}

}
