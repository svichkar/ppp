/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.WorkerDao;
import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Customer;
import com.nixsolutions.serviceStation.dbObjects.Worker;

/**
 * @author Михаил
 *
 */
public class WorkerDaoImpl implements WorkerDao {
	private final static Logger logger = LogManager.getLogger(WorkerDaoImpl.class);
	private DbConnector dbConnector;

	/*
	 * get All Workers
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getAllWorkers()
	 */
	public List<Worker> getAllWorkers() {
		List<Worker> workers = new ArrayList<Worker>();
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"SELECT * FROM worker w INNER JOIN worker_specialization ws WHERE w.specialization_id =ws.specialization_id \"");
			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"SELECT * FROM worker w INNER JOIN worker_specialization ws WHERE w.specialization_id =ws.specialization_id ");
			ResultSet set = stmt.executeQuery();
			dbConnector.closeConnection();
			logger.trace("Generate list of the worker objects");
			while (set.next()) {
				workers.add(new Worker(set.getString("specialization_id"), set.getString("last_name"),
						set.getString("first_name")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return workers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#
	 * getAllWorkersBySpecialization(java.lang.String)
	 */
	public List<Worker> getAllWorkersBySpecialization(String specialization) {
		List<Worker> workers = new ArrayList<Worker>();
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"SELECT * FROM worker WHERE specialization_id = ("
					+ "SELECT specialization_id FROM worker_specialization  WHERE specialization_name ='mechanik high');\"");
			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"SELECT * FROM worker WHERE specialization_id = ("
					+ "SELECT specialization_id FROM worker_specialization "
					+ "WHERE specialization_name ='?');");
			stmt.setString(1, specialization);
			ResultSet set = stmt.executeQuery();
			dbConnector.closeConnection();
			logger.trace("Generate list of the worker objects");
			while (set.next()) {
				workers.add(new Worker(specialization, set.getString("last_name"),
						set.getString("first_name")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return workers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getWorkerByLastName(
	 * java.lang.String)
	 */
	public Worker getWorker(String last_name, String first_name) {
			try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"SELECT * FROM worker w INNER JOIN worker_specialization ws WHERE w.specialization_id =ws.specialization_id "
					+ "AND w.last_name='Ivanov' AND w.first_name='?'\"");
			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"SELECT * FROM worker w "
					+ "INNER JOIN worker_specialization ws"
					+ "WHERE w.specialization_id =ws.specialization_id AND w.last_name='?' AND w.first_name='?';");
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			ResultSet set = stmt.executeQuery();
			dbConnector.closeConnection();
			logger.trace("Generate list of the worker objects");
			while (set.next()) {
				return new Worker(set.getString("specialization_id"), set.getString("last_name"),
						set.getString("first_name"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#
	 * getWorkerStatusByLastName(java.lang.String)
	 */
	public String getWorkerStatus(String last_name, String first_name) {
		try {
		logger.debug("Create DB connector");
		dbConnector = new DbConnector();
		logger.trace(
				"Send query \"SELECT status_name FROM status WHERE status_id =(SELECT status_id FROM worker_status WHERE worker_id =(SELECT worker_id FROM worker WHERE last_name =',' AND first_name =','));\"");
		PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
				"SELECT status_name FROM status WHERE status_id =(SELECT status_id FROM worker_status WHERE worker_id =(SELECT worker_id FROM worker WHERE last_name =',' AND first_name =','));");
		stmt.setString(1, last_name);
		stmt.setString(2, first_name);
		ResultSet set = stmt.executeQuery();
		dbConnector.closeConnection();
		logger.trace("Generate list of the worker objects");
		while (set.next()) {
			return set.getString("status_name");
		}
		stmt.close();
	} catch (SQLException e) {
		logger.error(e.getMessage(), e);
	} catch (ClassNotFoundException e) {
		logger.error(e.getMessage(), e);
	}
	return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#createWorker(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	public void createWorker(String specialization, String last_name, String first_name) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"INSERT INTO worker (last_name ,first_name ,specialization_id )VALUES('Petrov','Petr',(SELECT specialization_id FROM worker_specialization WHERE specialization_name ='electric'));\"");

			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("INSERT INTO worker (last_name ,first_name ,specialization_id )VALUES('?','?',(SELECT specialization_id FROM worker_specialization WHERE specialization_name ='?'));");
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			stmt.setString(3, specialization);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New worker was created");
			else
				logger.debug("New worker was not created");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#deleteWorker(java.
	 * lang.String, java.lang.String)
	 */
	public void deleteWorker(String last_name, String first_name) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"DELETE FROM worker WHERE last_name='?' AND first_name='?';\"");

			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("DELETE FROM worker WHERE last_name='?' AND first_name='?';");
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("worker was deleted");
			else
				logger.debug("worker was not deleted");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"CREATE TABLE worker( worker_id INT IDENTITY, specialization_id INT NOT NULL,FOREIGN KEY (specialization_id) REFERENCES  worker_specialization(specialization_id),first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL);\"");

			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"CREATE TABLE worker( worker_id INT IDENTITY, specialization_id INT NOT NULL,FOREIGN KEY (specialization_id) REFERENCES  worker_specialization(specialization_id),first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL);");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table worker was created");
			else
				logger.debug("Table worker was not created");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteTableWithAllData() {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"DROP TABLE worker ;\"");

			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("DROP TABLE worker ;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table worker was deleted");
			else
				logger.debug("table worker was not deleted");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}	}

}
