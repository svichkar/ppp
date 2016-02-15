package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.dto.WorkerStatusSpecification;
import com.nixsolutions.entities.Worker;

public interface WorkerService {

	List<Worker> getAllWorkers();

	Worker getWorkerById(long id);

	void addWorker(Worker worker);

	void updateWorker(Worker worker);

	void deleteWorker(Worker worker);

	WorkerStatusSpecification getWorkerStatusSpecificationByPK(long workerId);

	List<WorkerStatusSpecification> getAllWorkerStatusSpecification();

}
