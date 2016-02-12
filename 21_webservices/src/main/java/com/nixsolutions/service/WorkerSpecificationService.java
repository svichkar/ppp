package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.WorkerSpecification;

public interface WorkerSpecificationService {
	public WorkerSpecification getWorkerSpecificationByid(long id);

	public List<WorkerSpecification> getAllWorkerSpecifications();

	public void addWorkerSpecification(WorkerSpecification workerSpecification);

	public void updateWorkerSpecification(WorkerSpecification workerSpecification);

	public void deleteWorkerSpecification(WorkerSpecification workerSpecification);

}
