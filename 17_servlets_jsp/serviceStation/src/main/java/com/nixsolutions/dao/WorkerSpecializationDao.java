package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.WorkerSpecialization;

public interface WorkerSpecializationDao extends DBTables {
	/**
	 * \ create New Specialization
	 * 
	 * @
	 */
	public void createNewSpecialization(String specialization);

	/**
	 * get All Specialization
	 * 
	 * @
	 */
	public List<WorkerSpecialization> getAllSpecialization();

	/**
	 * delete Specialization By Name
	 * 
	 * @
	 */
	public void deleteSpecializationByName(String specialization);

	/**
	 * getSpecialization
	 * 
	 * @param specialization_id
	 * @return @
	 */
	public WorkerSpecialization getSpecialization(Integer specialization_id);

}
