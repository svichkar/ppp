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
	public void createNewSpecialization(String specialization);

	/**
	 * get All Specialization
	 * 
	 * @throws SQLException
	 */
	public List<WorkerSpecialization> getAllSpecialization();

	/**
	 * delete Specialization By Name
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 */
	public boolean deleteSpecializationByName(String specialization);

	/**
	 * getSpecialization
	 * 
	 * @param specialization_id
	 * @return
	 * @throws SQLException
	 */
	public WorkerSpecialization getSpecialization(Integer specialization_id);

	/**
	 * getSpecialization
	 * 
	 * @param specialization_id
	 * @return
	 * @throws SQLException
	 */
	public WorkerSpecialization getSpecialization(String specialization_id);

}
