package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.WorkerSpecification;

public interface WorkerSpecificationService {
	WorkerSpecification getWorkerSpecificationByid(long id);

	List<WorkerSpecification> getAllWorkerSpecifications();

	void addWorkerSpecification(WorkerSpecification workerSpecification);

	void updateWorkerSpecification(WorkerSpecification workerSpecification);

	void deleteWorkerSpecification(WorkerSpecification workerSpecification);

}
