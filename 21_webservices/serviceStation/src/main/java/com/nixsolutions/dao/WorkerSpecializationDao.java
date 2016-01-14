package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.WorkerSpecialization;

public interface WorkerSpecializationDao {
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
	WorkerSpecialization getSpecialization(Integer specialization_id);

}
