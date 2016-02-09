package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.WorkerSpecificationDAO;
import com.nixsolutions.entities.WorkerSpecification;
import com.nixsolutions.service.WorkerSpecificationService;

@Service
public class WorkerSpecificationServiceImpl implements WorkerSpecificationService {

	@Autowired
	private WorkerSpecificationDAO workerSpecificationDaoImpl;

	@Override
	public WorkerSpecification getWorkerSpecificationByid(long id) {
		return workerSpecificationDaoImpl.findByPK(id);
	}

	@Override
	public List<WorkerSpecification> getAllWorkerSpecifications() {
		return workerSpecificationDaoImpl.getAll();
	}

	@Override
	public void addWorkerSpecification(WorkerSpecification workerSpecification) {
		workerSpecificationDaoImpl.create(workerSpecification);
	}

	@Override
	public void updateWorkerSpecification(WorkerSpecification workerSpecification) {
		workerSpecificationDaoImpl.update(workerSpecification);
	}

	@Override
	public void deleteWorkerSpecification(WorkerSpecification workerSpecification) {
		workerSpecificationDaoImpl.delete(workerSpecification);
	}

}
