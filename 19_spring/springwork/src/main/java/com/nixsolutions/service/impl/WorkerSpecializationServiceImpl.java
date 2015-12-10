package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.dao.WorkerSpecializationDAO;
import com.nixsolutions.hibernate.entity.WorkerSpecialization;
import com.nixsolutions.service.WorkerSpecializationService;

import org.springframework.stereotype.Service;

@Service
public class WorkerSpecializationServiceImpl implements WorkerSpecializationService {

	@Autowired
	private WorkerSpecializationDAO specDao;

	public List<WorkerSpecialization> getAllSpecs() {
		return specDao.getAll();
	}

	public WorkerSpecialization getSpecById(int id) {
		return specDao.getByPK(id);
	}
}
