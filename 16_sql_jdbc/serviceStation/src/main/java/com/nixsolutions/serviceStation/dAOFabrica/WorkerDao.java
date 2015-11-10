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
	 * @return list of the workers with specialization
	 */
	public List<Worker> getAllWorkersBySpecialization(String specialization);

	/**
	 * get worker
	 * 
	 * @param last_name
	 *            worker last name
	 * @param first_name
	 *            worker first name
	 * @return worker object
	 */
	public Worker getWorker(String last_name, String first_name);

	/**
	 * get worker status
	 * 
	 * @param last_name
	 *            worker last name
	 * @param first_name
	 *            worker first name
	 * @return status name
	 */
	public String getWorkerStatus(String lastName, String first_name);

	/**
	 * create new worker in db
	 */
	public void createWorker(Worker worker);

	/**
	 * delete worker
	 * 
	 * @param last_name
	 *            worker last name
	 * 
	 * @param first_name
	 *            worker first name
	 */
	public void deleteWorker(String last_name, String first_name);

}
