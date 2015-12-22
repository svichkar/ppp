/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.WorkerSpecializationDao;
import com.nixsolutions.entity.WorkerSpecialization;
import com.nixsolutions.service.SpecializationService;

/**
 * @author mixeyes
 *
 */
@Service
public class SpecializationServiceImpl implements SpecializationService {

	@Autowired
	private WorkerSpecializationDao specDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.SpecializationService#createNewSpecialization(
	 * java.lang.String)
	 */
	@Override
	public void createNewSpecialization(String specialization) {
		specDao.createNewSpecialization(specialization);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.SpecializationService#getAllSpecialization()
	 */
	@Override
	public List<WorkerSpecialization> getAllSpecialization() {
		return specDao.getAllSpecialization();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.SpecializationService#deleteSpecializationByName
	 * (java.lang.String)
	 */
	@Override
	public boolean deleteSpecializationByName(String specialization) {
		return specDao.deleteSpecializationByName(specialization);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.SpecializationService#getSpecialization(java.
	 * lang.Integer)
	 */
	@Override
	public WorkerSpecialization getSpecialization(Integer specialization_id) {
		return specDao.getSpecialization(specialization_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.SpecializationService#getSpecialization(java.
	 * lang.Integer)
	 */
	@Override
	public WorkerSpecialization getSpecialization(String specialization_id) {
		return getSpecialization(Integer.decode(specialization_id));
	}

}
