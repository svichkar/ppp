/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import java.lang.reflect.Parameter;
import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Worker;

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
	 */
	public List<Worker> getAllWorkersBySpecialization(String specialization);

	/**
	 * get sqllab.worker 
	 * 
	 * @param last_name
	 *            sqllab.worker  last name
	 * @param first_name
	 *            sqllab.worker  first name
	 * @return sqllab.worker  object
	 */
	public Worker getWorker(String last_name, String first_name);

	/**
	 * get sqllab.worker  status
	 * 
	 * @param last_name
	 *            sqllab.worker  last name
	 * @param first_name
	 *            sqllab.worker  first name
	 * @return status name
	 */
	public String getWorkerStatus(String lastName, String first_name);

	/**
	 * create new sqllab.worker  in db
	 */
	public void createWorker(Worker worker );

	
	/**
	 * updateWorker
	 * @param worker
	 */
	public void updateWorker(Worker worker);
	/**
	 * delete sqllab.worker 
	 * 
	 * @param last_name
	 *            sqllab.worker  last name
	 * 
	 * @param first_name
	 *            sqllab.worker  first name
	 */
	public void deleteWorker(String last_name, String first_name);

}
