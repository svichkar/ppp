package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.hibernate.entity.WorkerSpecialization;

public interface WorkerSpecializationService {

	List<WorkerSpecialization> getAllSpecs();

	WorkerSpecialization getSpecById(int id);
}
