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
	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "CREATE TABLE sqllab.worker_status( " + "worker_status_id INT IDENTITY, "
					+ "worker_status_name VARCHAR(128) NOT NULL); ";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				LOGGER.trace("Table sqllab.worker_status was created");
			else
				LOGGER.debug("Table sqllab.worker_status was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DROP TABLE sqllab.worker_status ;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				LOGGER.trace(" table sqllab.worker_status was deleted");
			else
				LOGGER.debug("table sqllab.worker_status was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public List<WorkerStatus> getAllWorker_statuses() {
		List<WorkerStatus> workerStatus = new ArrayList<WorkerStatus>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.worker_status;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.worker_status objects");
			while (set.next()) {
				workerStatus.add(new WorkerStatus(set.getInt("worker_status_id"), set.getString("worker_status_name")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return workerStatus;
	}

	@Override
	public WorkerStatus getWorkerStatus(Integer worker_status_id) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.worker_status WHERE worker_status_id=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, worker_status_id);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate the worker_status object");
			while (set.next()) {
				return new WorkerStatus(set.getInt("worker_status_id"), set.getString("worker_status_name"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void createNewStatus(String statusName) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "INSERT INTO sqllab.worker_status (worker_status_name)VALUES(?);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, statusName);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.worker_status was created");
			else
				LOGGER.debug("New sqllab.worker_status was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteStatusByName(String statusName) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DELETE FROM sqllab.worker_status WHERE worker_status_name=?";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, statusName);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("worker_status was deleted");
			else
				LOGGER.debug("worker_status was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
