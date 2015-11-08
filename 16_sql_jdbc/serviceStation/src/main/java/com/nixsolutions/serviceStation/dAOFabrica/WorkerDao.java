/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Worker;

/**
 * @author Михаил
 *
 */
public interface WorkerDao extends DBTables{
	/*
	 * get All Workers
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getAllWorkers()
	 */
public List<Worker> getAllWorkers();

	public List<Worker> getAllWorkersBySpecialization(String specialization);

	public Worker getWorker(String last_name, String first_name);

	public String getWorkerStatus(String lastName, String first_name);

	public void createWorker(String specialization, String last_name, String first_name);

	public void deleteWorker(String last_name, String first_name);

}
