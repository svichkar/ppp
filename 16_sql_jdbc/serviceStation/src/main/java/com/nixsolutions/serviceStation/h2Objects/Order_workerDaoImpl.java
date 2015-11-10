/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao;
import com.nixsolutions.serviceStation.dbCommon.DbConnector;

/**
 * @author mixeyes
 *
 */
public class Order_workerDaoImpl implements Order_workerDao {

	private final static Logger logger = LogManager.getLogger(Order_workerDaoImpl.class);
	private DbConnector dbConnector;

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"CREATE TABLE order_worker( "
					+ "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), "
					+ "worker_id INT IDENTITY, "
					+ "FOREIGN KEY (worker_id) REFERENCES  worker(worker_id), "
					+ "isCompleted BOOLEAN NOT NULL DEFAULT false);\"");

			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(
					"CREATE TABLE order_worker( "
							+ "order_id INT NOT NULL,"
							+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), "
							+ "worker_id INT IDENTITY, "
							+ "FOREIGN KEY (worker_id) REFERENCES  worker(worker_id), "
							+ "isCompleted BOOLEAN NOT NULL DEFAULT false);");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table order_worker was created");
			else
				logger.debug("Table order_worker was not created");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData()
	 */
	@Override
	public void deleteTableWithAllData() {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace("Send query \"DROP TABLE order_worker;\"");

			PreparedStatement stmt = dbConnector.getConnection().prepareStatement("DROP TABLE order_worker;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table order_worker was deleted");
			else
				logger.debug("table order_worker was not deleted");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#assignWorkerToOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void assignWorkerToOrder(Integer order_id, Integer worker_id) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"INSERT INTO order_worker (order_id  ,worker_id, isCompleted)VALUES('AUDI','1234567890qwertyu',1);\"");

			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(""
					+ "INSERT INTO order_worker (order_id  ,worker_id, isCompleted)"
					+ "VALUES(?,?,false);");
			stmt.setInt(1, order_id);
			stmt.setInt(2, worker_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New order_worker was created");
			else
				logger.debug("New order_worker was not created");
			dbConnector.closeConnection();
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#changeStatus(java.lang.Integer, java.lang.Integer, boolean)
	 */
	@Override
	public void changeStatus(Integer order_id, Integer worker_id, boolean isCompleted) {
		try {
			logger.debug("Create DB connector");
			dbConnector = new DbConnector();
			logger.trace(
					"Send query \"SELECT * FROM car WHERE customer_id =(SELECT customer_id FROM customer WHERE first_name ='Alex' AND last_name ='Alkov');\"");
			// SELECT * FROM car WHERE customer_id =(SELECT customer_id FROM
			// customer WHERE first_name ='Alex' AND last_name ='Alkov');
			PreparedStatement stmt = dbConnector.getConnection().prepareStatement(""
					+ "UPDATE order_worker "
					+ "SET isCompleted=? "
					+ "WHERE order_id=? AND worker_id=?;");
			stmt.setBoolean(1, isCompleted);
			stmt.setInt(2, order_id);
			stmt.setInt(3, worker_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New order_worker was updated");
			else
				logger.debug("New order_worker was not updated");
			dbConnector.closeConnection();
			logger.trace("Generate list of the car objects");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
