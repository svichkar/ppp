/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.Worker_statusDao;
import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Order_status;
import com.nixsolutions.serviceStation.dbObjects.Worker;
import com.nixsolutions.serviceStation.dbObjects.Worker_status;

/**
 * @author mixeyes
 *
 */
public class Worker_statusDaoImpl implements Worker_statusDao {
	private final static Logger logger = LogManager.getLogger(Order_statusDaoImpl.class);
	private Connection dbConnector;

	public Worker_statusDaoImpl(Connection dbConnector) {
		super();
		this.dbConnector = dbConnector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"CREATE TABLE worker_status( "
					+ "worker_status_id INT IDENTITY, "
					+ "worker_status_name VARCHAR(128) NOT NULL);\"");

			PreparedStatement stmt = dbConnector.prepareStatement(
					"CREATE TABLE worker_status( "
							+ "worker_status_id INT IDENTITY, "
							+ "worker_status_name VARCHAR(128) NOT NULL); ");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace("Table worker_status was created");
			else
				logger.debug("Table worker_status was not created");
			stmt.close();
		} catch (SQLException e) {
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
			logger.trace("Send query \"DROP TABLE worker_status ;\"");

			PreparedStatement stmt = dbConnector.prepareStatement("DROP TABLE worker_status ;");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table worker_status was deleted");
			else
				logger.debug("table worker_status was not deleted");
				stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public List<Worker_status> getAllWorker_statuses() {
		List<Worker_status> worker_status = new ArrayList<Worker_status>();
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"SELECT * FROM worker_status;\"");
			PreparedStatement stmt = dbConnector.prepareStatement(
					"SELECT * FROM worker_status;");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the worker_status objects");
			while (set.next()) {
				worker_status.add(new Worker_status(set.getInt("worker_status_id"),set.getString("worker_status_name")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return worker_status;
	}

	@Override
	public void createNewStatus(String statusName) {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"INSERT INTO worker_status (worker_status_name)VALUES('?');\"");

			PreparedStatement stmt = dbConnector.prepareStatement(
					"INSERT INTO worker_status (worker_status_name)VALUES('?');");
			stmt.setString(1, statusName);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New worker_status was created");
			else
				logger.debug("New worker_status was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteStatusByName(String statusName) {
		try {
			logger.debug("Create DB connector");
				logger.trace("Send query \"DELETE FROM worker_status WHERE worker_status_name='?';\"");

			PreparedStatement stmt = dbConnector
					.prepareStatement("DELETE FROM worker_status WHERE worker_status_name='?'");
			stmt.setString(1, statusName);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("worker_status was deleted");
			else
				logger.debug("worker_status was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}


}
