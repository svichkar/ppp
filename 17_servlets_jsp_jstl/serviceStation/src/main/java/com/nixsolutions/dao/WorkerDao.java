/**
 * 
 */
package com.nixsolutions.dao;

import java.lang.reflect.Parameter;
import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.Worker;

/**
 * @author Михаил
 *
 */
@SuppressWarnings("unused")
public interface WorkerDao extends DBTables {
	/*
	 * get All Workers
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getAllWorkers()
	 */
	public List<Worker> getAllWorkers();

	/**
	 * get All Workers By Specialization
	 * 
	 * @param specialization
	 *            specialization name
	 * @return list of the sqllab.worker s with specialization
	 * @throws SQLException
	 */
	public List<Worker> getAllWorkersBySpecialization(String specialization);

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
	public Worker getWorker(String last_name, String first_name);

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
	public String getWorkerStatus(String lastName, String first_name);

	/**
	 * create new sqllab.worker in db
	 * 
	 * @throws SQLException
	 */
	public void createWorker(Worker worker);

	/**
	 * updateWorker
	 * 
	 * @param worker
	 * @throws SQLException
	 */
	public void updateWorker(Worker worker);

	/**
	 * delete sqllab.worker
	 * 
	 * @param last_name
	 *            sqllab.worker last name
	 * 
	 * @param first_name
	 *            sqllab.worker first name
	 * @throws SQLException
	 */
	public void deleteWorker(String last_name, String first_name);

	/**
	 * getWorker
	 * 
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public Worker getWorker(Integer user_id);

	/**
	 * getWorkerByID
	 * 
	 * @param worker_id
	 * @return
	 */
	public Worker getWorkerByID(Integer worker_id);

}
