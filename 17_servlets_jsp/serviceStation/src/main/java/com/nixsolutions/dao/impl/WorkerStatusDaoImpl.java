/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.WorkerStatusDao;
import com.nixsolutions.entity.WorkerStatus;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class WorkerStatusDaoImpl implements WorkerStatusDao {
	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "CREATE TABLE sqllab.worker_status( " + "worker_status_id INT IDENTITY, "
					+ "worker_status_name VARCHAR(128) NOT NULL); ";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace("Table sqllab.worker_status was created");
			else
				logger.debug("Table sqllab.worker_status was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData(
	 * )
	 */
	@Override
	public void deleteTableWithAllData() throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DROP TABLE sqllab.worker_status ;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table sqllab.worker_status was deleted");
			else
				logger.debug("table sqllab.worker_status was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.WorkerStatusDao#getAllWorker_statuses()
	 */
	@Override
	public List<WorkerStatus> getAllWorker_statuses() throws SQLException {
		List<WorkerStatus> workerStatus = new ArrayList<WorkerStatus>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.worker_status;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.worker_status objects");
			while (set.next()) {
				workerStatus.add(new WorkerStatus(set.getInt("worker_status_id"), set.getString("worker_status_name")));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
		return workerStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.WorkerStatusDao#getWorkerStatus(java.lang.Integer)
	 */
	@Override
	public WorkerStatus getWorkerStatus(Integer worker_status_id) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.worker_status WHERE worker_status_id=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, worker_status_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate the worker_status object");
			while (set.next()) {
				return new WorkerStatus(set.getInt("worker_status_id"), set.getString("worker_status_name"));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.dao.WorkerStatusDao#createNewStatus(java.lang.String)
	 */
	@Override
	public void createNewStatus(String statusName) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "INSERT INTO sqllab.worker_status (worker_status_name)VALUES(?);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, statusName);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.worker_status was created");
			else
				logger.debug("New sqllab.worker_status was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.dao.WorkerStatusDao#deleteStatusByName(java.lang.String)
	 */
	@Override
	public void deleteStatusByName(String statusName) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DELETE FROM sqllab.worker_status WHERE worker_status_name=?";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, statusName);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("worker_status was deleted");
			else
				logger.debug("worker_status was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}
}
