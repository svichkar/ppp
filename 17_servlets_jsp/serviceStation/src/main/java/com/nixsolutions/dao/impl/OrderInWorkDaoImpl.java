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
	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"CREATE TABLE sqllab.worker ( worker_id INT IDENTITY, "
					+ "specialization_id INT NOT NULL,FOREIGN KEY (specialization_id) REFERENCES worker_specialization(specialization_id), "
					+ "first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL);\"");

			Statement stmt = connection.createStatement();
			stmt.addBatch("CREATE TABLE sqllab.order_in_work( " + "order_id INT IDENTITY, "
					+ "order_description VARCHAR(512) NOT NULL, " + "datetime_start TIMESTAMP NOT NULL, "
					+ "datetime_finish TIMESTAMP);");
			stmt.addBatch("ALTER TABLE sqllab.order_in_work " + "ADD COLUMN  car_id INT NOT NULL;");
			stmt.addBatch(
					"ALTER TABLE sqllab.order_in_work " + "ADD FOREIGN KEY(car_id ) REFERENCES sqllab.car (car_id );");

			stmt.addBatch("ALTER TABLE sqllab.order_in_work " + "ADD COLUMN order_status_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE sqllab.order_in_work "
					+ "ADD FOREIGN KEY(order_status_id ) REFERENCES sqllab.order_status (order_status_id );");
			stmt.executeBatch();
			logger.trace("Table sqllab.order_in_work was created");
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
	 * @see
	 * com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData(
	 * )
	 */
	@Override
	public void deleteTableWithAllData() throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"DROP TABLE sqllab.order_in_work ;\"");

			PreparedStatement stmt = connection.prepareStatement("DROP TABLE sqllab.order_in_work ;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table sqllab.order_in_work was deleted");
			else
				logger.debug("table sqllab.order_in_work was not deleted");
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
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getAllOrders(
	 * )
	 */
	@Override
	public List<OrderInWork> getAllOrders() throws SQLException {
		List<OrderInWork> order_in_works = new ArrayList<OrderInWork>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"SELECT * FROM sqllab.order_in_work;\"");
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sqllab.order_in_work;");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.order_in_work objects");
			while (set.next()) {
				order_in_works.add(new OrderInWork(set.getInt("order_id"), set.getString("order_description"),
						set.getDate("datetime_start"), set.getDate("datetime_finish"), set.getInt("order_status_id"),
						set.getInt("car_id")));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
		return order_in_works;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getOrderByCar
	 * ()
	 */
	@Override
	public OrderInWork getOrderInWorkByCar(String reg_number) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.order_in_work oiw " + "INNER JOIN sqllab.order_status os "
					+ "INNER JOIN sqllab.car sc " + "WHERE oiw.order_status_id = os.order_status_id "
					+ "AND oiw.car_id = sc.car_id AND sc.reg_number=?;";
			logger.trace("Send query \"" + query + "\"");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, reg_number);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.worker  objects");
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
				connection.close();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#
	 * getOrderByCustomer()
	 */
	@Override
	public OrderInWork getOrderInWorkByCustomer(String last_name, String first_name) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.order_in_work oiw " + "INNER JOIN sqllab.order_status os "
					+ "WHERE oiw.order_status_id = os.order_status_id " + "AND sqllab.car .customer_id=("
					+ "SELECT customer_id " + "FROM sqllab.customer " + "WHERE last_name =? AND first_name =?) "
					+ "AND oiw.ORDER_STATUS_ID =2;";
			logger.trace("Send query \"" + query + "\"");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.worker  objects");
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
				connection.close();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getOrderByID(
	 * )
	 */
	@Override
	public OrderInWork getOrderByID(Integer order_id) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.order_in_work oiw" + "INNER JOIN sqllab.order_status os "
					+ "INNER JOIN sqllab.car sqllab.car  " + "WHERE oiw.order_status_id = os.order_status_id "
					+ "AND oiw.car_id = sqllab.car .car_id " + "AND oiw.order_id=?;";
			logger.trace("Send query \"" + query + "\"");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, order_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.worker  objects");
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
				connection.close();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.OrderInWorkDao#createNewOrder(java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public void createNewOrder(Integer car_id, String description) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "INSERT INTO sqllab.order_in_work (order_description,datetime_start, datetime_finish, order_status_id ,car_ID)"
					+ "VALUES(?,CURRENT_TIMESTAMP(), null,'1',?);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, description);
			stmt.setInt(2, car_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("sqllab.order_in_work was created");
			else
				logger.debug("sqllab.order_in_work was not created");
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
	 * @see
	 * com.nixsolutions.dao.OrderInWorkDao#changeOrderStatusByOrderID(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public void changeOrderStatusByOrderID(Integer order_id, Integer order_status_id) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "UPDATE sqllab.order_in_work SET order_status_id=? WHERE order_id=?;";
			logger.trace("Send query \"" + query + "\"");
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, order_status_id);
			stmt.setInt(2, order_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("status was changed");
			else
				logger.debug("status was not changed");
			logger.trace("Generate list of the sqllab.order_in_work objects");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}
}
