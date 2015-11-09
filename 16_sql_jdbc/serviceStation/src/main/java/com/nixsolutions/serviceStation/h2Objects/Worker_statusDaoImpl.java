/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.Worker_statusDao;
import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Status;

/**
 * @author mixeyes
 *
 */
public class Worker_statusDaoImpl implements Worker_statusDao {
	private final static Logger logger = LogManager.getLogger(StatusDaoImpl.class);
	private DbConnector dbConnector;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"CREATE TABLE worker_status( worker_id INT NOT NULL,FOREIGN KEY (worker_id) REFERENCES  worker(worker_id), status_id INT NOT NULL,FOREIGN KEY (status_id) REFERENCES  status(status_id));\"");

			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"CREATE TABLE worker_status( worker_id INT NOT NULL,FOREIGN KEY (worker_id) REFERENCES  worker(worker_id), status_id INT NOT NULL,FOREIGN KEY (status_id) REFERENCES  status(status_id));");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace("Table worker_status was created");
			else
				logger.debug("Table worker_status was not created");
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
	 * com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData(
	 * )
	 */
	public void deleteTableWithAllData() {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"DROP TABLE worker_status ;\"");

			PreparedStatement stmt = dbConnector.getConnection().prepareStatement("DROP TABLE worker_status ;");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table worker_status was deleted");
			else
				logger.debug("table worker_status was not deleted");
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
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Worker_statusDao#
	 * getWorkerStatus(java.lang.String, java.lang.String)
	 */
	public String getWorkerStatus(String last_name, String first_name) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"SELECT status_name FROM status WHERE status_id =(SELECT status_id FROM worker_status WHERE worker_id =(SELECT worker_id FROM worker WHERE last_name ='?' AND first_name ='?'));\"");
			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"SELECT status_name FROM status WHERE status_id =(SELECT status_id FROM worker_status WHERE worker_id =(SELECT worker_id FROM worker WHERE last_name ='?' AND first_name ='?'));");
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			ResultSet set = stmt.executeQuery();
			dbConnector.closeConnection();
			logger.trace("Generate list of the status objects");
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
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Worker_statusDao#
	 * setStatusToWorker(java.lang.String, java.lang.String)
	 */
	public void setStatusToWorker(String last_name, String first_name, String status) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"UPDATE worker_status SET status_id =(SELECT status_id FROM status WHERE status_name ='?') WHERE worker_id =(SELECT worker_id FROM worker WHERE last_name ='?' AND first_name ='?');\"");
			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"UPDATE worker_status SET status_id =(SELECT status_id FROM status WHERE status_name ='?') WHERE worker_id =(SELECT worker_id FROM worker WHERE last_name ='?' AND first_name ='?');");
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table worker_status was updated");
			else
				logger.debug("table worker_status was not updated");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
