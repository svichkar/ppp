/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

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
	private DbConnector dbConnector;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"CREATE TABLE worker( worker_id INT IDENTITY, "
					+ "specialization_id INT NOT NULL,FOREIGN KEY (specialization_id) REFERENCES worker_specialization(specialization_id), "
					+ "first_name VARCHAR(128) NOT NULL,last_name VARCHAR(128) NOT NULL);\"");

			Statement stmt = dbConnector.getConnection().createStatement();
			stmt.addBatch(
					"CREATE TABLE order_in_work( "
					+ "order_id INT IDENTITY, "
					+ "description VARCHAR(512) NOT NULL, "
					+ "datetime_start TIMESTAMP NOT NULL, "
					+ "datetime_finish TIMESTAMP);");
			stmt.addBatch("ALTER TABLE order_in_work "
					+ "ADD COLUMN car_id INT NOT NULL;");
			stmt.addBatch("ALTER TABLE order_in_work "
					+ "ADD FOREIGN KEY(car_id ) REFERENCES car (car_id );");
			stmt.executeBatch();
			logger.trace("Table order_in_work was created");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
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
			dbConnector = new DbConnector();
			logger.trace("Send query \"DROP TABLE order_in_work ;\"");

			PreparedStatement stmt = dbConnector.getConnection().prepareStatement("DROP TABLE order_in_work ;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table order_in_work was deleted");
			else
				logger.debug("table order_in_work was not deleted");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
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
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"SELECT * FROM order_in_work oiw INNER JOIN ORDER_STATUS  os WHERE oiw.ORDER_STATUS_ID   =os.ORDER_STATUS_ID ;\"");
			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"SELECT * FROM order_in_work oiw"
					+ "INNER JOIN order_status os "
					+ "INNER JOIN car car "
					+ "WHERE oiw.order_status_id = os.order_status_id "
					+ "AND oiw.car_id = car.car_id;");
			ResultSet set = stmt.executeQuery();
			dbConnector.closeConnection();
			logger.trace("Generate list of the worker objects");
			while (set.next()) {
				order_in_works.add(new Order_in_work(set.getInt("order_id"),set.getString("order_description"),set.getDate("datetime_start"),set.getDate("datetime_finish"),
						set.getString("order_status_name"),
						new Car(set.getInt("car_id"),set.getString("model"),set.getString("vin_number"),set.getString("car_description"),set.getInt("customer_id"))));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
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
	public Order_in_work getOrderByCar() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#
	 * getOrderByCustomer()
	 */
	@Override
	public Order_in_work getOrderByCustomer() {
		// TODO Auto-generated method stub
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
	public Order_in_work getOrderByID() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#
	 * createNewOrder(com.nixsolutions.serviceStation.dbObjects.Car,
	 * java.lang.String)
	 */
	@Override
	public void createNewOrder(Car car, String description) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#
	 * setOrderStatus(java.lang.String,
	 * com.nixsolutions.serviceStation.dbObjects.Order_in_work)
	 */
	@Override
	public void setOrderStatus(String status, Order_in_work order_in_work) {
		// TODO Auto-generated method stub

	}

}
