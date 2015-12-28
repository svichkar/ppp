package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.OrderStatusDao;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.util.ConnectionManager;

public class OrderStatusDaoImpl implements OrderStatusDao {

	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public void createNewStatus(String status) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "INSERT INTO sqllab.order_status (order_status_name)VALUES(?);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, status);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New status was created");
			else
				LOGGER.debug("New status was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public List<OrderStatus> getAllStatus() {
		List<OrderStatus> statuses = new ArrayList<OrderStatus>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.order_status";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the status objects");
			while (set.next()) {
				statuses.add(new OrderStatus(set.getInt("order_status_id"), set.getString("order_status_name")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return statuses;
	}

	@Override
	public OrderStatus getStatusByID(Integer status_id) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.order_status WHERE order_status_id = ?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, status_id);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the status objects");
			while (set.next()) {
				return new OrderStatus(set.getInt("order_status_id"), set.getString("order_status_name"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void deleteStatusByName(String status) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DELETE FROM sqllab.order_status WHERE order_status_name=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, status);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("status was deleted");
			else
				LOGGER.debug("status was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "CREATE TABLE sqllab.order_status( " + "order_status_id INT IDENTITY, "
					+ "order_status_name VARCHAR(128) NOT NULL);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("Table sqllab.order_status was created");
			else
				LOGGER.debug("Table sqllab.order_status was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DROP TABLE sqllab.order_status ;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace(" table status was deleted");
			else
				LOGGER.debug("table status was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
