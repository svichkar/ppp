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

/**
 * @author Михаил
 *
 */
public class WorkerDaoImpl implements WorkerDao {
	private final static Logger logger = LogManager.getLogger();

	/*
	 * get All Workers
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getAllWorkers()
	 */
	@Override
	public List<Worker> getAllWorkers() {
		List<Worker> workers = new ArrayList<Worker>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.worker  w INNER JOIN sqllab.worker_specialization ws WHERE w.specialization_id =ws.specialization_id;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.worker  objects");
			while (set.next()) {
				workers.add(
						new Worker(set.getInt("worker_id"), set.getInt("specialization_id"), set.getString("last_name"),
								set.getString("first_name"), set.getInt("worker_status_id"), set.getInt("user_id")));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

		return workers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#
	 * getAllWorkersBySpecialization(java.lang.String)
	 */
	@Override
	public List<Worker> getAllWorkersBySpecialization(String specialization) {
		List<Worker> workers = new ArrayList<Worker>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.worker  WHERE specialization_id = ("
					+ "SELECT specialization_id FROM sqllab.worker_specialization " + "WHERE specialization_name =?);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, specialization);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.worker  objects");
			while (set.next()) {
				workers.add(
						new Worker(set.getInt("worker_id"), set.getInt("specialization_id"), set.getString("last_name"),
								set.getString("first_name"), set.getInt("worker_status_id"), set.getInt("user_id")));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
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
	@Override
	public Worker getWorker(String last_name, String first_name) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.worker  " + "WHERE last_name=? AND first_name=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				return new Worker(set.getInt("worker_id"), set.getInt("specialization_id"), set.getString("last_name"),
						set.getString("first_name"), set.getInt("worker_status_id"), set.getInt("user_id"));

			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getWorker(java.lang.
	 * Integer)
	 */
	@Override
	public Worker getWorker(Integer user_id) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.worker  " + "WHERE user_id=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, user_id);
			ResultSet set = stmt.executeQuery();
			logger.debug("Generate the worker object");
			while (set.next()) {
				return new Worker(set.getInt("worker_id"), set.getInt("specialization_id"), set.getString("last_name"),
						set.getString("first_name"), set.getInt("worker_status_id"), set.getInt("user_id"));

			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#
	 * getWorkerStatusByLastName(java.lang.String)
	 */
	@Override
	public String getWorkerStatus(String last_name, String first_name) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT status_name FROM status WHERE status_id =("
					+ "SELECT status_id FROM sqllab.worker_status WHERE worker_id =("
					+ "SELECT worker_id FROM sqllab.worker  WHERE last_name =',' AND first_name =','));";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.worker  objects");
			while (set.next()) {
				return set.getString("status_name");
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
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
	@Override
	public void createWorker(Worker worker) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "INSERT INTO sqllab.worker  (last_name ,first_name ,specialization_id,worker_status_id, user_id )"
					+ "VALUES(?,?,?,?,?);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, worker.getLast_name());
			stmt.setString(2, worker.getFirst_name());
			stmt.setInt(3, worker.getSpecialization_id());
			stmt.setInt(4, worker.getWorker_status_id());
			stmt.setInt(5, worker.getUser_id());
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.worker  was created");
			else
				logger.debug("New sqllab.worker  was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.WorkerDao#updateWorker(com.nixsolutions.entity.
	 * Worker)
	 */
	@Override
	public void updateWorker(Worker worker) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "UPDATE sqllab.worker "
					+ "SET last_name =?,first_name =?,specialization_id=?,worker_status_id =? " + "WHERE worker_id=?";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, worker.getLast_name());
			stmt.setString(2, worker.getFirst_name());
			stmt.setInt(3, worker.getSpecialization_id());
			stmt.setInt(4, worker.getWorker_status_id());
			stmt.setInt(5, worker.getWorker_id());
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.worker  was created");
			else
				logger.debug("New sqllab.worker  was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#deleteWorker(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void deleteWorker(String last_name, String first_name) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DELETE FROM sqllab.worker  WHERE last_name=? AND first_name=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("worker was deleted");
			else
				logger.debug("worker was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "CREATE TABLE sqllab.worker( " + "worker_id INT IDENTITY, "
					+ "specialization_id INT NOT NULL,"
					+ "FOREIGN KEY (specialization_id) REFERENCES  sqllab.worker_specialization(specialization_id),"
					+ "first_name VARCHAR(128) NOT NULL," + "last_name VARCHAR(128) NOT NULL, "
					+ "worker_status_id INT NOT NULL, "
					+ "FOREIGN KEY (worker_status_id) REFERENCES sqllab.worker_status(worker_status_id),"
					+ "user_id INT NOT NULL, " + "FOREIGN KEY (user_id) REFERENCES sqllab.user(user_id));";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table sqllab.worker  was created");
			else
				logger.debug("Table sqllab.worker  was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.DBTables#deleteTableWithAllData()
	 */
	@Override
	public void deleteTableWithAllData() {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DROP TABLE sqllab.worker;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table sqllab.worker  was deleted");
			else
				logger.debug("table sqllab.worker  was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.WorkerDao#getWorker(java.lang.
	 * Integer)
	 */
	@Override
	public Worker getWorkerByID(Integer worker_id) {
		try (Connection connection= ConnectionManager.getConnection();){
			String query = "SELECT * FROM sqllab.worker  " + "WHERE worker_id=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, worker_id);
			ResultSet set = stmt.executeQuery();
			logger.debug("Generate the worker object");
			while (set.next()) {
				return new Worker(set.getInt("worker_id"), set.getInt("specialization_id"), set.getString("last_name"),
						set.getString("first_name"), set.getInt("worker_status_id"), set.getInt("user_id"));

			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} 

		return null;
	}

}
