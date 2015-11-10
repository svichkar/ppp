/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao;
import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Car;
import com.nixsolutions.serviceStation.dbObjects.Order_in_work;
import com.nixsolutions.serviceStation.dbObjects.Worker;

/**
 * @author mixeyes
 *
 */
public class Order_in_workDaoImpl implements Order_in_workDao {
	private final static Logger logger = LogManager.getLogger(Order_in_workDaoImpl.class);
	private Connection dbConnector;

	public Order_in_workDaoImpl(Connection connection) {
		this.dbConnector = connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"CREATE TABLE worker( worker_id INT IDENTITY, "
					+ "specialization_id INT NOT NULL,FOREIGN KEY (specialization_id) REFERENCES worker_specialization(specialization_id), "
					+ "first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL);\"");

			Statement stmt = dbConnector.createStatement();
			stmt.addBatch("CREATE TABLE order_in_work( " + "order_id INT IDENTITY, "
					+ "order_description VARCHAR(512) NOT NULL, " + "datetime_start TIMESTAMP NOT NULL, "
					+ "datetime_finish TIMESTAMP);");
			stmt.addBatch("ALTER TABLE order_in_work " + "ADD COLUMN car_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE order_in_work " + "ADD FOREIGN KEY(car_id ) REFERENCES car (car_id );");
			stmt.executeBatch();
			logger.trace("Table order_in_work was created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DROP TABLE order_in_work ;\"");

			PreparedStatement stmt = dbConnector.prepareStatement("DROP TABLE order_in_work ;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table order_in_work was deleted");
			else
				logger.debug("table order_in_work was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
	public List<Order_in_work> getAllOrders() {
		List<Order_in_work> order_in_works = new ArrayList<Order_in_work>();
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"SELECT * FROM order_in_work oiw INNER JOIN ORDER_STATUS  os WHERE oiw.ORDER_STATUS_ID   =os.ORDER_STATUS_ID ;\"");
			PreparedStatement stmt = dbConnector.prepareStatement(
					"SELECT * FROM order_in_work oiw" + "INNER JOIN order_status os " + "INNER JOIN car car "
							+ "WHERE oiw.order_status_id = os.order_status_id " + "AND oiw.car_id = car.car_id;");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the worker objects");
			while (set.next()) {
				order_in_works.add(new Order_in_work(set.getInt("order_id"), set.getString("order_description"),
						set.getDate("datetime_start"), set.getDate("datetime_finish"), set.getInt("order_status_id"),
						set.getInt("car_id")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
	public Order_in_work getOrderInWorkByCar(String vin_number) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM order_in_work oiw" + "INNER JOIN order_status os "
					+ "INNER JOIN car car " + "WHERE oiw.order_status_id = os.order_status_id "
					+ "AND oiw.car_id = car.car_id;\"");
			PreparedStatement stmt = dbConnector.prepareStatement(
					"SELECT * FROM order_in_work oiw" + "INNER JOIN order_status os " + "INNER JOIN car car "
							+ "WHERE oiw.order_status_id = os.order_status_id " + "AND oiw.car_id = car.car_id "
							+ "AND car.vin_number='?' " + "AND oiw.order_status_id=2;");
			stmt.setString(1, vin_number);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the worker objects");
			while (set.next()) {
				return new Order_in_work(set.getInt("order_id"), set.getString("order_description"),
						set.getDate("datetime_start"), set.getDate("datetime_finish"), set.getInt("order_status_id"),
						set.getInt("car_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
	public Order_in_work getOrderInWorkByCustomer(String last_name, String first_name) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM order_in_work oiw" + "INNER JOIN order_status os "
					+ "INNER JOIN car car " + "WHERE oiw.order_status_id = os.order_status_id "
					+ "AND oiw.car_id = car.car_id;\"");
			PreparedStatement stmt = dbConnector.prepareStatement(
					"SELECT * FROM order_in_work oiw" + "INNER JOIN order_status os " + "INNER JOIN car car "
							+ "WHERE oiw.order_status_id = os.order_status_id " + "AND oiw.car_id = car.car_id "
							+ "AND car.customer_id=(" + "SELECT customer_id " + "FROM customer "
							+ "WHERE last_name ='?' AND first_name ='?') " + "AND oiw.ORDER_STATUS_ID =2;");
			stmt.setString(1, last_name);
			stmt.setString(2, first_name);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the worker objects");
			while (set.next()) {
				return new Order_in_work(set.getInt("order_id"), set.getString("order_description"),
						set.getDate("datetime_start"), set.getDate("datetime_finish"), set.getInt("order_status_id"),
						set.getInt("car_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
	public Order_in_work getOrderByID(Integer order_id) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM order_in_work oiw" + "INNER JOIN order_status os "
					+ "INNER JOIN car car " + "WHERE oiw.order_status_id = os.order_status_id "
					+ "AND oiw.car_id = car.car_id;\"");
			PreparedStatement stmt = dbConnector
					.prepareStatement("SELECT * FROM order_in_work oiw" + "INNER JOIN order_status os "
							+ "INNER JOIN car car " + "WHERE oiw.order_status_id = os.order_status_id "
							+ "AND oiw.car_id = car.car_id " + "AND oiw.order_id=?;");
			stmt.setInt(1, order_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the worker objects");
			while (set.next()) {
				return new Order_in_work(set.getInt("order_id"), set.getString("order_description"),
						set.getDate("datetime_start"), set.getDate("datetime_finish"), set.getInt("order_status_id"),
						set.getInt("car_id"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void createNewOrder(Integer car_id, String description) {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"INSERT INTO order_in_work (order_description,datetime_start ,order_status_id ,car_ID  )"
							+ "VALUES('?',CURRENT_TIMESTAMP(),'1',?);\"");

			PreparedStatement stmt = dbConnector.prepareStatement(
					"INSERT INTO order_in_work (order_description,datetime_start ,order_status_id ,car_ID)"
							+ "VALUES('?',CURRENT_TIMESTAMP(),'1',?);");
			stmt.setString(1, description);
			stmt.setInt(2, car_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New order_in_work was created");
			else
				logger.debug("New order_in_work was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void changeOrderStatusByOrderID(Integer order_id, Integer order_status_id) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"UPDATE car SET order_status_id=? WHERE order_id=?;\"");
			PreparedStatement stmt = dbConnector.prepareStatement("UPDATE car SET order_status_id=? WHERE order_id=?;");
			stmt.setInt(1, order_status_id);
			stmt.setInt(2, order_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("status was changed");
			else
				logger.debug("status was not changed");
			logger.trace("Generate list of the car objects");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
