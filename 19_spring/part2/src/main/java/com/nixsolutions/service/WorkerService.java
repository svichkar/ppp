package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.Worker;
import com.nixsolutions.entities.WorkerStatusSpecification;

public interface WorkerService {

	public List<Worker> getAllWorkers();

	public Worker getWorkerById(long id);

	public void addWorker(Worker worker);

	public void updateWorker(Worker worker);

	public void deleteWorker(Worker worker);
	
	public WorkerStatusSpecification getWorkerStatusSpecificationByPK(long workerId);
	
	public List<WorkerStatusSpecification> getAllWorkerStatusSpecification();
	
}
