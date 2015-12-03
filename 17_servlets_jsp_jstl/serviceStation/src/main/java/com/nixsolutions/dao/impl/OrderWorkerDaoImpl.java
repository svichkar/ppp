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

import com.nixsolutions.dao.OrderWorkerDao;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class OrderWorkerDaoImpl implements OrderWorkerDao {

	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#getAll()
	 */
	@Override
	public List<OrderWorker> getAll() {
		List<OrderWorker> order_workers = new ArrayList<>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.order_worker;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.order_worker objects");
			while (set.next()) {
				order_workers.add(new OrderWorker(set.getInt("order_id"), set.getInt("worker_id"),
						set.getBoolean("isCompleted")));
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

		return order_workers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "CREATE TABLE sqllab.order_worker ( " + "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), " + "worker_id INT IDENTITY, "
					+ "FOREIGN KEY (worker_id) REFERENCES  sqllab.worker (worker_id), "
					+ "isCompleted BOOLEAN NOT NULL DEFAULT false);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace("Table sqllab.order_worker  was created");
			else
				logger.debug("Table sqllab.order_worker  was not created");
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
	 * com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData(
	 * )
	 */
	@Override
	public void deleteTableWithAllData() {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DROP TABLE sqllab.order_worker ;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table sqllab.order_worker  was deleted");
			else
				logger.debug("table sqllab.order_worker  was not deleted");
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
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#
	 * assignWorkerToOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void assignWorkerToOrder(Integer order_id, Integer worker_id) {
		try (Connection connection = ConnectionManager.getConnection()) {

			String query = "INSERT INTO sqllab.order_worker  (order_id  ,worker_id, isCompleted)"
					+ "VALUES(?,?,false); ";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, order_id);
			stmt.setInt(2, worker_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.order_worker  was created");
			else
				logger.debug("New sqllab.order_worker  was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#changeStatus(
	 * java.lang.Integer, java.lang.Integer, boolean)
	 */
	@Override
	public void changeStatus(Integer order_id, Integer worker_id, boolean isCompleted) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "UPDATE sqllab.order_worker  " + "SET isCompleted=? " + "WHERE order_id=? AND worker_id=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setBoolean(1, isCompleted);
			stmt.setInt(2, order_id);
			stmt.setInt(3, worker_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.order_worker  was updated");
			else
				logger.debug("New sqllab.order_worker  was not updated");
			logger.trace("Generate list of the sqllab.car objects");
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
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#
	 * assignWorkerToOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public OrderInWork getActiveOrderByWorkerID(Integer worker_id) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.order_in_work WHERE (order_status_id =1 OR order_status_id =2) "
					+ "AND order_id =(SELECT order_id FROM sqllab.order_worker WHERE worker_id =?);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, worker_id);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				return new OrderInWork(set.getInt("order_id"), set.getString("order_description"),
						set.getDate("datetime_start"), set.getDate("datetime_finish"), set.getInt("order_status_id"),
						set.getInt("car_id"));
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
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#
	 * assignWorkerToOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<OrderWorker> getWorkersByOrderID(Integer order_id) {
		List<OrderWorker> workers = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.order_worker WHERE order_id =?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, order_id);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				workers.add(new OrderWorker(set.getInt("order_id"), set.getInt("worker_id"),
						set.getBoolean("isCompleted")));
			}
			stmt.close();
			return workers;
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return null;

	}
}
