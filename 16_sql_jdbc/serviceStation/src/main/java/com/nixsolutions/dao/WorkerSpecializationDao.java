package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.WorkerSpecialization;

public interface WorkerSpecializationDao extends DBTables {
	/**
	 * \ create New Specialization
	 * 
	 * @throws SQLException
	 */
	public void createNewSpecialization(String specialization) throws SQLException;

	/**
	 * get All Specialization
	 * 
	 * @throws SQLException
	 */
	public List<WorkerSpecialization> getAllSpecialization() throws SQLException;

	/**
	 * delete Specialization By Name
	 * 
	 * @throws SQLException
	 */
	public void deleteSpecializationByName(String specialization) throws SQLException;

	/**
	 * getSpecialization
	 * 
	 * @param specialization_id
	 * @return
	 * @throws SQLException 
	 */
	public WorkerSpecialization getSpecialization(Integer specialization_id) throws SQLException;

}
