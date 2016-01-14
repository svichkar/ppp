/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.Worker;

/**
 * @author Михаил
 *
 */

public interface WorkerDao {
	/*
	 * get All Workers
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getAllWorkers()
	 */
	List<Worker> getAllWorkers();

	/**
	 * get All Workers By Specialization
	 * 
	 * @param specialization
	 *            specialization name
	 * @return list of the sqllab.worker s with specialization
	 * @throws SQLException
	 */
	List<Worker> getAllWorkersBySpecialization(String specialization);

	/**
	 * get sqllab.worker
	 * 
	 * @param last_name
	 *            sqllab.worker last name
	 * @param first_name
	 *            sqllab.worker first name
	 * @return sqllab.worker object
	 * @throws SQLException
	 */
	Worker getWorker(String last_name, String first_name);

	/**
	 * get sqllab.worker status
	 * 
	 * @param last_name
	 *            sqllab.worker last name
	 * @param first_name
	 *            sqllab.worker first name
	 * @return status name
	 * @throws SQLException
	 */
	String getWorkerStatus(String lastName, String first_name);

	/**
	 * create new sqllab.worker in db
	 * 
	 * @throws SQLException
	 */
	void createWorker(Worker worker);

	/**
	 * updateWorker
	 * 
	 * @param worker
	 * @throws SQLException
	 */
	void updateWorker(Worker worker);

	/**
	 * delete sqllab.worker
	 * 
	 * @param last_name
	 *            sqllab.worker last name
	 * 
	 * @param first_name
	 *            sqllab.worker first name
	 * @return
	 * @throws SQLException
	 */
	boolean deleteWorker(String last_name, String first_name);

	/**
	 * deleteWorker
	 * 
	 * @param worker
	 * @throws SQLException
	 */
	void deleteWorker(Worker worker);

	/**
	 * getWorker
	 * 
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	Worker getWorker(Integer user_id);

	/**
	 * getWorkerByID
	 * 
	 * @param worker_id
	 * @return
	 */
	Worker getWorkerByID(Integer worker_id);

}
