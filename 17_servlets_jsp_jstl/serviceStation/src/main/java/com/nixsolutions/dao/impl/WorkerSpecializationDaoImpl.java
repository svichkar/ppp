package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.WorkerSpecializationDao;
import com.nixsolutions.entity.WorkerSpecialization;
import com.nixsolutions.util.ConnectionManager;

public class WorkerSpecializationDaoImpl implements WorkerSpecializationDao {

	private final static Logger logger = LogManager.getLogger();

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
			String query = "CREATE TABLE sqllab.worker_specialization( specialization_id INT IDENTITY,specialization_name VARCHAR(256) NOT NULL);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table sqllab.worker_specialization was created");
			else
				logger.debug("Table sqllab.worker_specialization was not created");
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
			String query = "DROP TABLE sqllab.worker_specialization ;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table sqllab.worker_specialization was deleted");
			else
				logger.debug("table sqllab.customer was not deleted");
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
	 * com.nixsolutions.dao.WorkerSpecializationDao#createNewSpecialization(java
	 * .lang.String)
	 */
	@Override
	public void createNewSpecialization(String specialization) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "INSERT INTO sqllab.worker_specialization (specialization_name)VALUES(?);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, specialization);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.worker_specialization was created");
			else
				logger.debug("New sqllab.worker_specialization was not created");
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
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Worker_specializationDao#
	 * getAllSpecialization()
	 */
	@Override
	public List<WorkerSpecialization> getAllSpecialization() {
		List<WorkerSpecialization> specializations = new ArrayList<WorkerSpecialization>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.worker_specialization";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.worker_specialization objects");
			while (set.next()) {
				specializations.add(new WorkerSpecialization(set.getInt("specialization_id"),
						set.getString("specialization_name")));
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

		return specializations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Worker_specializationDao#
	 * getSpecialization(java.lang.Integer)
	 */
	@Override
	public WorkerSpecialization getSpecialization(Integer specialization_id) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.worker_specialization WHERE specialization_id=?";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, specialization_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.worker_specialization objects");
			while (set.next()) {
				return new WorkerSpecialization(set.getInt("specialization_id"), set.getString("specialization_name"));
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
	 * com.nixsolutions.dao.WorkerSpecializationDao#deleteSpecializationByName(
	 * java.lang.String)
	 */
	@Override
	public void deleteSpecializationByName(String specialization) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DELETE FROM sqllab.worker_specialization WHERE specialization_name=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, specialization);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("specialization was deleted");
			else
				logger.debug("specialization was not deleted");
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

}
