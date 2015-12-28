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
	public List<Worker> getAllWorkers() ;

	/**
	 * get All Workers By Specialization
	 * 
	 * @param specialization
	 *            specialization name
	 * @return list of the sqllab.worker s with specialization
	 * @
	 */
	public List<Worker> getAllWorkersBySpecialization(String specialization) ;

	/**
	 * get sqllab.worker
	 * 
	 * @param lastName
	 *            sqllab.worker last name
	 * @param firstName
	 *            sqllab.worker first name
	 * @return sqllab.worker object
	 * @
	 */
	public Worker getWorker(String lastName, String firstName) ;

	/**
	 * get sqllab.worker status
	 * 
	 * @param lastName
	 *            sqllab.worker last name
	 * @param firstName
	 *            sqllab.worker first name
	 * @return status name
	 * @
	 */
	public String getWorkerStatus(String lastName, String firstName) ;

	/**
	 * create new sqllab.worker in db
	 * 
	 * @
	 */
	public void createWorker(Worker worker) ;

	/**
	 * updateWorker
	 * 
	 * @param worker
	 * @ 
	 */
	public void updateWorker(Worker worker) ;

	/**
	 * delete sqllab.worker
	 * 
	 * @param lastName
	 *            sqllab.worker last name
	 * 
	 * @param firstName
	 *            sqllab.worker first name
	 * @ 
	 */
	public void deleteWorker(String lastName, String firstName) ;

	/**
	 * getWorker
	 * 
	 * @param userId
	 * @return
	 * @
	 */
	public Worker getWorker(Integer userId) ;

}
