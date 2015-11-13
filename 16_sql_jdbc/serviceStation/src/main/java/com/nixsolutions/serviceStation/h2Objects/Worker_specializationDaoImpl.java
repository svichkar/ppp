package com.nixsolutions.serviceStation.h2Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.Worker_specializationDao;
import com.nixsolutions.serviceStation.dbObjects.Worker_specialization;

public class Worker_specializationDaoImpl implements Worker_specializationDao {

	private final static Logger logger = LogManager.getLogger();
	private Connection dbConnector;

	public Worker_specializationDaoImpl(Connection dbConnector) {
		this.dbConnector = dbConnector;
	}

	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"CREATE TABLE sqllab.worker_specialization( specialization_id INT IDENTITY,specialization_name VARCHAR(256) NOT NULL);\"");

			PreparedStatement stmt = dbConnector.prepareStatement(
					"CREATE TABLE sqllab.worker_specialization( specialization_id INT IDENTITY,specialization_name VARCHAR(256) NOT NULL);");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table sqllab.worker_specialization was created");
			else
				logger.debug("Table sqllab.worker_specialization was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteTableWithAllData() {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DROP TABLE sqllab.worker_specialization ;\"");

			PreparedStatement stmt = dbConnector.prepareStatement("DROP TABLE sqllab.worker_specialization ;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table sqllab.worker_specialization was deleted");
			else
				logger.debug("table sqllab.customer was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void createNewSpecialization(String specialization) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"INSERT INTO sqllab.worker_specialization (specialization_name)VALUES(?);\"");

			PreparedStatement stmt = dbConnector
					.prepareStatement("INSERT INTO sqllab.worker_specialization (specialization_name)VALUES(?);");
			stmt.setString(1, specialization);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.worker_specialization was created");
			else
				logger.debug("New sqllab.worker_specialization was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public List<Worker_specialization> getAllSpecialization() {
		List<Worker_specialization> specializations = new ArrayList<Worker_specialization>();
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM worker_specialization\"");
			PreparedStatement stmt = dbConnector.prepareStatement("SELECT * FROM sqllab.worker_specialization");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.worker_specialization objects");
			while (set.next()) {
				specializations.add(new Worker_specialization(set.getInt("specialization_id"),
						set.getString("specialization_name")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return specializations;
	}

	public void deleteSpecializationByName(String specialization) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DELETE FROM sqllab.worker_specialization WHERE specialization_name=?;\"");

			PreparedStatement stmt = dbConnector
					.prepareStatement("DELETE FROM sqllab.worker_specialization WHERE specialization_name=?;");
			stmt.setString(1, specialization);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("specialization was deleted");
			else
				logger.debug("specialization was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
