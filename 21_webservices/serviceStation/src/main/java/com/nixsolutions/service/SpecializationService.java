package com.nixsolutions.service;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.WorkerSpecialization;

public interface SpecializationService {
	/**
	 * \ create New Specialization
	 * 
	 * @throws SQLException
	 */
	void createNewSpecialization(String specialization);

	/**
	 * get All Specialization
	 * 
	 * @throws SQLException
	 */
	List<WorkerSpecialization> getAllSpecialization();

	/**
	 * delete Specialization By Name
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 */
	boolean deleteSpecializationByName(String specialization);

	/**
	 * getSpecialization
	 * 
	 * @param specialization_id
	 * @return
	 * @throws SQLException
	 */
	WorkerSpecialization getSpecialization(Integer specializationId);

	/**
	 * getSpecialization
	 * 
	 * @param specialization_id
	 * @return
	 * @throws SQLException
	 */
	WorkerSpecialization getSpecialization(String specializationId);

}
