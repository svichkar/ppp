package com.nixsolutions.serviceStation.h2Objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.StatusDao;
import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Status;

public class StatusDaoImpl implements StatusDao {

	private final static Logger logger = LogManager.getLogger(StatusDaoImpl.class);
	private DbConnector dbConnector;

	public void createNewStatus(String status) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"INSERT INTO status (status_name)VALUES('?');\"");

			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("INSERT INTO status (status_name)VALUES('?');");
			stmt.setString(1, status);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New status was created");
			else
				logger.debug("New status was not created");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public List<Status> getAllStatus() {
		List<Status> statuses = new ArrayList<Status>();
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"SELECT * FROM status\"");
			PreparedStatement stmt = dbConnector.getConnection().prepareStatement("SELECT * FROM status");
			ResultSet set = stmt.executeQuery();
			dbConnector.closeConnection();
			logger.trace("Generate list of the status objects");
			while (set.next()) {
				statuses.add(new Status(set.getInt("status_id"), set.getString("status_name")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return statuses;
	}

	public void deleteStatusByName(String status) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"DELETE FROM status WHERE status_name='?';\"");

			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("DELETE FROM status WHERE status_name='?';");
			stmt.setString(1, status);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("status was deleted");
			else
				logger.debug("status was not deleted");
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
					"Send query \"CREATE TABLE status( status_id INT IDENTITY, status_name VARCHAR(128) NOT NULL);\"");

			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"CREATE TABLE status( status_id INT IDENTITY, status_name VARCHAR(128) NOT NULL);");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table status was created");
			else
				logger.debug("Table status was not created");
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
			logger.trace("Send query \"DROP TABLE status ;\"");

			PreparedStatement stmt = dbConnector.getConnection()
					.prepareStatement("DROP TABLE status ;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table status was deleted");
			else
				logger.debug("table status was not deleted");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}	}

}
