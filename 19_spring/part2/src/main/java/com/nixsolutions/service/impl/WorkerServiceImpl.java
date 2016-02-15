package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dto.WorkerStatusSpecification;
import com.nixsolutions.entities.Worker;
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
			workerStatusSpecification.setFname(worker.getFname());
			workerStatusSpecification.setLname(worker.getLname());
			workerStatusSpecification.setId(worker.getWorkerId());
			workerStatusSpecification.setSpecId(worker.getSpec().getSpecId());
			workerStatusSpecification.setSpecName(worker.getSpec().getSpecName());
			workerStatusSpecification.setStatusId(worker.getStatus().getStatusId());
			workerStatusSpecification.setStatusName(worker.getStatus().getStatusName());
		}
		return workerStatusSpecification;
	}

	@Override
	public List<WorkerStatusSpecification> getAllWorkerStatusSpecification() {
		List<WorkerStatusSpecification> workerStatusSpecifications = new ArrayList<>();
		List<Worker> workers = workerDaoImpl.getAll();
		for (Worker worker : workers) {
			WorkerStatusSpecification workerStatusSpecification = new WorkerStatusSpecification();
			workerStatusSpecification.setFname(worker.getFname());
			workerStatusSpecification.setLname(worker.getLname());
			workerStatusSpecification.setId(worker.getWorkerId());
			workerStatusSpecification.setSpecId(worker.getSpec().getSpecId());
			workerStatusSpecification.setSpecName(worker.getSpec().getSpecName());
			workerStatusSpecification.setStatusId(worker.getStatus().getStatusId());
			workerStatusSpecification.setStatusName(worker.getStatus().getStatusName());
			workerStatusSpecifications.add(workerStatusSpecification);
		}
		return workerStatusSpecifications;

	}

}
