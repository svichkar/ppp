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
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class OrderWorkerDaoImpl implements OrderWorkerDao {

	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public List<OrderWorker> getAll() {
		List<OrderWorker> order_workers = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.order_worker;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.order_worker objects");
			while (set.next()) {
				order_workers.add(new OrderWorker(set.getInt("order_id"), set.getInt("worker_id"),
						set.getBoolean("isCompleted")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return order_workers;
	}

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "CREATE TABLE sqllab.order_worker ( " + "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), " + "worker_id INT IDENTITY, "
					+ "FOREIGN KEY (worker_id) REFERENCES  sqllab.worker (worker_id), "
					+ "isCompleted BOOLEAN NOT NULL DEFAULT false);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				LOGGER.trace("Table sqllab.order_worker  was created");
			else
				LOGGER.debug("Table sqllab.order_worker  was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DROP TABLE sqllab.order_worker ;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				LOGGER.trace(" table sqllab.order_worker  was deleted");
			else
				LOGGER.debug("table sqllab.order_worker  was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void assignWorkerToOrder(Integer orderId, Integer worker_id) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "INSERT INTO sqllab.order_worker  (order_id  ,worker_id, isCompleted)"
					+ "VALUES(?,?,false);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, orderId);
			stmt.setInt(2, worker_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.order_worker  was created");
			else
				LOGGER.debug("New sqllab.order_worker  was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void changeStatus(Integer orderId, Integer worker_id, boolean isCompleted) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "UPDATE sqllab.order_worker  " + "SET isCompleted=? " + "WHERE orderId=? AND worker_id=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setBoolean(1, isCompleted);
			stmt.setInt(2, orderId);
			stmt.setInt(3, worker_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.order_worker  was updated");
			else
				LOGGER.debug("New sqllab.order_worker  was not updated");
			LOGGER.trace("Generate list of the sqllab.car objects");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
