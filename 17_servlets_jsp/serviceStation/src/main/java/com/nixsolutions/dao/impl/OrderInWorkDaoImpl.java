/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.OrderInWorkDao;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class OrderInWorkDaoImpl implements OrderInWorkDao {
	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"CREATE TABLE sqllab.worker ( worker_id INT IDENTITY, "
					+ "specialization_id INT NOT NULL,FOREIGN KEY (specialization_id) REFERENCES worker_specialization(specialization_id), "
					+ "first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL);\"");

			Statement stmt = connection.createStatement();
			stmt.addBatch("CREATE TABLE sqllab.order_in_work( " + "order_id INT IDENTITY, "
					+ "order_description VARCHAR(512) NOT NULL, " + "datetime_start TIMESTAMP NOT NULL, "
					+ "datetime_finish TIMESTAMP);");
			stmt.addBatch("ALTER TABLE sqllab.order_in_work " + "ADD COLUMN  carId INT NOT NULL;");
			stmt.addBatch(
					"ALTER TABLE sqllab.order_in_work " + "ADD FOREIGN KEY(carId ) REFERENCES sqllab.car (carId );");

			stmt.addBatch("ALTER TABLE sqllab.order_in_work " + "ADD COLUMN orderStatusId INT NOT NULL;");
			stmt.addBatch("ALTER TABLE sqllab.order_in_work "
					+ "ADD FOREIGN KEY(order_status_id ) REFERENCES sqllab.order_status (order_status_id );");
			stmt.executeBatch();
			LOGGER.trace("Table sqllab.order_in_work was created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"DROP TABLE sqllab.order_in_work ;\"");

			PreparedStatement stmt = connection.prepareStatement("DROP TABLE sqllab.order_in_work ;");
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace(" table sqllab.order_in_work was deleted");
			else
				LOGGER.debug("table sqllab.order_in_work was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public List<OrderInWork> getAllOrders() {
		List<OrderInWork> order_in_works = new ArrayList<OrderInWork>();
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"SELECT * FROM sqllab.order_in_work;\"");
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.order_in_work;");
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.order_in_work objects");
			while (set.next()) {
				order_in_works.add(new OrderInWork(set.getInt("order_id"), set.getString("order_description"),
						set.getDate("datetime_start"), set.getDate("datetime_finish"), set.getInt("order_status_id"),
						set.getInt("car_id")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return order_in_works;
	}

	@Override
	public OrderInWork getOrderInWorkByCar(String regNumber) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.order_in_work oiw " + "INNER JOIN sqllab.order_status os "
					+ "INNER JOIN sqllab.car sc " + "WHERE oiw.order_status_id = os.order_status_id "
					+ "AND oiw.car_id = sc.car_id AND sc.reg_number=?;";
			LOGGER.trace("Send query \"" + query + "\"");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, regNumber);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.worker  objects");
			while (set.next()) {
				return new OrderInWork(set.getInt("order_id"), set.getString("order_description"),
						set.getDate("datetime_start"), set.getDate("datetime_finish"), set.getInt("order_status_id"),
						set.getInt("car_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public OrderInWork getOrderInWorkByCustomer(String lastName, String firstName) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.order_in_work oiw " + "INNER JOIN sqllab.order_status os "
					+ "WHERE oiw.orderStatusId = os.orderStatusId " + "AND sqllab.car .customerId=("
					+ "SELECT customerId " + "FROM sqllab.customer " + "WHERE lastName =? AND firstName =?) "
					+ "AND oiw.orderStatusId =2;";
			LOGGER.trace("Send query \"" + query + "\"");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.worker  objects");
			while (set.next()) {
				return new OrderInWork(set.getInt("order_id"), set.getString("order_description"),
						set.getDate("datetime_start"), set.getDate("datetime_finish"), set.getInt("order_status_id"),
						set.getInt("carId"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public OrderInWork getOrderByID(Integer orderId) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.order_in_work oiw" + "INNER JOIN sqllab.order_status os "
					+ "INNER JOIN sqllab.car sqllab.car  " + "WHERE oiw.orderStatusId = os.orderStatusId "
					+ "AND oiw.carId = sqllab.car .carId " + "AND oiw.orderId=?;";
			LOGGER.trace("Send query \"" + query + "\"");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, orderId);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.worker  objects");
			while (set.next()) {
				return new OrderInWork(set.getInt("order_id"), set.getString("order_description"),
						set.getDate("datetime_start"), set.getDate("datetime_finish"), set.getInt("order_status_id"),
						set.getInt("carId"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void createNewOrder(Integer carId, String description) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "INSERT INTO sqllab.order_in_work ("
					+ "order_description,datetime_start, datetime_finish, order_status_id ,car_id)"
					+ "VALUES(?,CURRENT_TIMESTAMP(), null,'1',?);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, description);
			stmt.setInt(2, carId);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("sqllab.order_in_work was created");
			else
				LOGGER.debug("sqllab.order_in_work was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void changeOrderStatusByOrderID(Integer orderId, Integer orderStatusId) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "UPDATE sqllab.order_in_work SET order_status_id=? WHERE order_id=?;";
			LOGGER.trace("Send query \"" + query + "\"");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, orderStatusId);
			stmt.setInt(2, orderId);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("status was changed");
			else
				LOGGER.debug("status was not changed");
			LOGGER.trace("Generate list of the sqllab.order_in_work objects");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
