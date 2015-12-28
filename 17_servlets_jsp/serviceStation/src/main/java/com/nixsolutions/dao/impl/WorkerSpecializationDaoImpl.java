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

	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "CREATE TABLE sqllab.worker_specialization( specialization_id INT IDENTITY,specialization_name VARCHAR(256) NOT NULL);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("Table sqllab.worker_specialization was created");
			else
				LOGGER.debug("Table sqllab.worker_specialization was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DROP TABLE sqllab.worker_specialization ;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace(" table sqllab.worker_specialization was deleted");
			else
				LOGGER.debug("table sqllab.customer was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void createNewSpecialization(String specialization) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "INSERT INTO sqllab.worker_specialization (specialization_name)VALUES(?);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, specialization);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.worker_specialization was created");
			else
				LOGGER.debug("New sqllab.worker_specialization was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public List<WorkerSpecialization> getAllSpecialization() {
		List<WorkerSpecialization> specializations = new ArrayList<WorkerSpecialization>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.worker_specialization";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.worker_specialization objects");
			while (set.next()) {
				specializations.add(new WorkerSpecialization(set.getInt("specialization_id"),
						set.getString("specialization_name")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return specializations;
	}

	@Override
	public WorkerSpecialization getSpecialization(Integer specialization_id) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.worker_specialization WHERE specialization_id=?";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, specialization_id);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.worker_specialization objects");
			while (set.next()) {
				return new WorkerSpecialization(set.getInt("specialization_id"), set.getString("specialization_name"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void deleteSpecializationByName(String specialization) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DELETE FROM sqllab.worker_specialization WHERE specialization_name=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, specialization);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("specialization was deleted");
			else
				LOGGER.debug("specialization was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
