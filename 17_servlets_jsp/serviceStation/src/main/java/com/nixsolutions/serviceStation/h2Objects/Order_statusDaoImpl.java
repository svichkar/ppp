package com.nixsolutions.serviceStation.h2Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.Order_statusDao;
import com.nixsolutions.serviceStation.dbObjects.Order_status;

public class Order_statusDaoImpl implements Order_statusDao {

	private final static Logger logger = LogManager.getLogger();
	private Connection dbConnector;
	
	public Order_statusDaoImpl(Connection connection) {
		this.dbConnector=connection;
	}

	public void createNewStatus(String status) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"INSERT INTO sqllab.order_status (order_status_name)VALUES(?);\"");

			PreparedStatement stmt = dbConnector
					.prepareStatement("INSERT INTO sqllab.order_status (order_status_name)VALUES(?);");
			stmt.setString(1, status);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New status was created");
			else
				logger.debug("New status was not created");
				stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public List<Order_status> getAllStatus() {
		List<Order_status> statuses = new ArrayList<Order_status>();
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM status\"");
			PreparedStatement stmt = dbConnector.prepareStatement("SELECT * FROM sqllab.order_status");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the status objects");
			while (set.next()) {
				statuses.add(new Order_status(set.getInt("order_status_id"), set.getString("order_status_name")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return statuses;
	}

	public Order_status getStatusByID(Integer status_id) {
		List<Order_status> statuses = new ArrayList<Order_status>();
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM status\"");
			PreparedStatement stmt = dbConnector.prepareStatement("SELECT * FROM sqllab.order_status WHERE order_status_id = ?;");
			stmt.setInt(1, status_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the status objects");
			while (set.next()) {
				return new Order_status(set.getInt("order_status_id"), set.getString("order_status_name"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void deleteStatusByName(String status) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DELETE FROM status WHERE order_status_name=?;\"");

			PreparedStatement stmt = dbConnector
					.prepareStatement("DELETE FROM sqllab.order_status WHERE order_status_name=?;");
			stmt.setString(1, status);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("status was deleted");
			else
				logger.debug("status was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"CREATE TABLE sqllab.order_status( status_id INT IDENTITY, status_name VARCHAR(128) NOT NULL);\"");

			PreparedStatement stmt = dbConnector.prepareStatement("CREATE TABLE sqllab.order_status( " + "order_status_id INT IDENTITY, "
							+ "order_status_name VARCHAR(128) NOT NULL);");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table sqllab.order_status was created");
			else
				logger.debug("Table sqllab.order_status was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteTableWithAllData() {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DROP TABLE sqllab.order_status ;\"");

			PreparedStatement stmt = dbConnector
					.prepareStatement("DROP TABLE sqllab.order_status ;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table status was deleted");
			else
				logger.debug("table status was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}	}

}
