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

	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.OrderStatusDao#createNewStatus(java.lang.String)
	 */
	@Override
	public void createNewStatus(String status) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "INSERT INTO sqllab.order_status (order_status_name)VALUES(?);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, status);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New status was created");
			else
				logger.debug("New status was not created");
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
	 * @see com.nixsolutions.dao.OrderStatusDao#getAllStatus()
	 */
	@Override
	public List<OrderStatus> getAllStatus() throws SQLException {
		List<OrderStatus> statuses = new ArrayList<OrderStatus>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.order_status";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the status objects");
			while (set.next()) {
				statuses.add(new OrderStatus(set.getInt("order_status_id"), set.getString("order_status_name")));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
		return statuses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.OrderStatusDao#getStatusByID(java.lang.Integer)
	 */
	@Override
	public OrderStatus getStatusByID(Integer status_id) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.order_status WHERE order_status_id = ?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, status_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the status objects");
			while (set.next()) {
				return new OrderStatus(set.getInt("order_status_id"), set.getString("order_status_name"));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.OrderStatusDao#deleteStatusByName(java.lang.String)
	 */
	@Override
	public void deleteStatusByName(String status) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DELETE FROM sqllab.order_status WHERE order_status_name=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, status);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("status was deleted");
			else
				logger.debug("status was not deleted");
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
	 * @see com.nixsolutions.dao.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "CREATE TABLE sqllab.order_status( " + "order_status_id INT IDENTITY, "
					+ "order_status_name VARCHAR(128) NOT NULL);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table sqllab.order_status was created");
			else
				logger.debug("Table sqllab.order_status was not created");
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
	 * @see com.nixsolutions.dao.DBTables#deleteTableWithAllData()
	 */
	@Override
	public void deleteTableWithAllData() throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DROP TABLE sqllab.order_status ;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table status was deleted");
			else
				logger.debug("table status was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}
}
