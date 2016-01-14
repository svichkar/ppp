package com.nixsolutions.service;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.entity.WorkerSpecialization;

public interface WorkerService {
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
	Worker getWorker(String lastName, String firstName);

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
	String getWorkerStatus(String lastName, String firstName);

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
	boolean deleteWorker(String lastName, String firstName);

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
	void deleteWorker(Worker worker);

	/**
	 * getWorker
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	Worker getWorker(Integer userId);

	/**
	 * getWorkerByID
	 * 
	 * @param workerId
	 * @return
	 */
	Worker getWorkerByID(Integer workerId);

	/**
	 * getWorkerByID
	 * 
	 * @param workerId
	 * @return
	 */
	Worker getWorkerByID(String workerId);

	void createWorker(String lastName, String firstName, String userLogin, WorkerSpecialization spec);

	void updateWorker(String workerId, User userByLogin, String lastName, String firstName,
			WorkerSpecialization specialization);

}
