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

import com.nixsolutions.dao.WorkerDao;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.util.ConnectionManager;

public class WorkerDaoImpl implements WorkerDao {
	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public List<Worker> getAllWorkers() {
		List<Worker> workers = new ArrayList<Worker>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.worker  w INNER JOIN sqllab.worker_specialization ws WHERE w.specialization_id =ws.specialization_id;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.worker  objects");
			while (set.next()) {
				workers.add(
						new Worker(set.getInt("worker_id"), set.getInt("specialization_id"), set.getString("last_name"),
								set.getString("first_name"), set.getInt("worker_status_id"), set.getInt("user_id")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return workers;
	}

	@Override
	public List<Worker> getAllWorkersBySpecialization(String specialization) {
		List<Worker> workers = new ArrayList<Worker>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.worker  WHERE specialization_id = ("
					+ "SELECT specialization_id FROM sqllab.worker_specialization " + "WHERE specialization_name =?);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, specialization);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.worker  objects");
			while (set.next()) {
				workers.add(
						new Worker(set.getInt("worker_id"), set.getInt("specialization_id"), set.getString("last_name"),
								set.getString("first_name"), set.getInt("worker_status_id"), set.getInt("user_id")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return workers;
	}

	@Override
	public Worker getWorker(String lastName, String firstName) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.worker  " + "WHERE lastName=? AND firstName=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				return new Worker(set.getInt("worker_id"), set.getInt("specialization_id"), set.getString("last_name"),
						set.getString("first_name"), set.getInt("worker_status_id"), set.getInt("user_id"));

			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Worker getWorker(Integer userId) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.worker  " + "WHERE user_id=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet set = stmt.executeQuery();
			LOGGER.debug("Generate the worker object");
			while (set.next()) {
				return new Worker(set.getInt("worker_id"), set.getInt("specialization_id"), set.getString("last_name"),
						set.getString("first_name"), set.getInt("worker_status_id"), set.getInt("user_id"));

			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String getWorkerStatus(String lastName, String firstName) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT status_name FROM status WHERE status_id =("
					+ "SELECT status_id FROM sqllab.worker_status WHERE worker_id =("
					+ "SELECT worker_id FROM sqllab.worker  WHERE lastName =',' AND firstName =','));";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.worker  objects");
			while (set.next()) {
				return set.getString("status_name");
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void createWorker(Worker worker) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "INSERT INTO sqllab.worker"
					+ " (last_name ,first_name ,specialization_id,worker_status_id, user_id )"
					+ "VALUES(?,?,?,?,?);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, worker.getLastName());
			stmt.setString(2, worker.getFirstName());
			stmt.setInt(3, worker.getSpecializationId());
			stmt.setInt(4, worker.getWorkerStatusId());
			stmt.setInt(5, worker.getUserId());
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.worker  was created");
			else
				LOGGER.debug("New sqllab.worker  was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void updateWorker(Worker worker) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "UPDATE sqllab.worker "
					+ "SET lastName =?,first_name =?,specialization_id=?,worker_status_id =? " + "WHERE worker_id=?";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, worker.getLastName());
			stmt.setString(2, worker.getFirstName());
			stmt.setInt(3, worker.getSpecializationId());
			stmt.setInt(4, worker.getWorkerStatusId());
			stmt.setInt(5, worker.getWorkerId());
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.worker  was created");
			else
				LOGGER.debug("New sqllab.worker  was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteWorker(String lastName, String firstName) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DELETE FROM sqllab.worker  WHERE lastName=? AND firstName=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("worker was deleted");
			else
				LOGGER.debug("worker was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "CREATE TABLE sqllab.worker( " + "worker_id INT IDENTITY, "
					+ "specialization_id INT NOT NULL,"
					+ "FOREIGN KEY (specialization_id) REFERENCES  sqllab.worker_specialization(specialization_id),"
					+ "first_name VARCHAR(128) NOT NULL," + "last_name VARCHAR(128) NOT NULL, "
					+ "worker_status_id INT NOT NULL, "
					+ "FOREIGN KEY (worker_status_id) REFERENCES sqllab.worker_status(worker_status_id),"
					+ "user_id INT NOT NULL, " + "FOREIGN KEY (user_id) REFERENCES sqllab.user(user_id));";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("Table sqllab.worker  was created");
			else
				LOGGER.debug("Table sqllab.worker  was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DROP TABLE sqllab.worker;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace(" table sqllab.worker  was deleted");
			else
				LOGGER.debug("table sqllab.worker  was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
