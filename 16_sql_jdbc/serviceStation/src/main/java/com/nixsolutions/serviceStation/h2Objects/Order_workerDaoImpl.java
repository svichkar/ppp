/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao;
import com.nixsolutions.serviceStation.dbObjects.Customer;
import com.nixsolutions.serviceStation.dbObjects.Order_worker;

/**
 * @author mixeyes
 *
 */
public class Order_workerDaoImpl implements Order_workerDao {

	private final static Logger logger = LogManager.getLogger();
	private Connection dbConnector;
	

	public Order_workerDaoImpl(Connection dbConnector) {
		this.dbConnector = dbConnector;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#getAll()
	 */
	public List<Order_worker> getAll(){
		List<Order_worker> order_workers = new ArrayList<>();
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM sqllab.order_worker\"");
			PreparedStatement stmt = dbConnector.prepareStatement("SELECT * FROM sqllab.order_worker;");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.order_worker objects");
			while (set.next()) {
				order_workers.add(new Order_worker(set.getInt("order_id"), set.getInt("worker_id"),
						set.getBoolean("isCompleted")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return order_workers;
	}
	
	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"CREATE TABLE sqllab.order_worker ( "
					+ "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), "
					+ "worker_id INT IDENTITY, "
					+ "FOREIGN KEY (worker_id) REFERENCES  sqllab.worker (worker_id), "
					+ "isCompleted BOOLEAN NOT NULL DEFAULT false);\"");

			PreparedStatement stmt = dbConnector.prepareStatement(
					"CREATE TABLE sqllab.order_worker ( "
							+ "order_id INT NOT NULL,"
							+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), "
							+ "worker_id INT IDENTITY, "
							+ "FOREIGN KEY (worker_id) REFERENCES  sqllab.worker (worker_id), "
							+ "isCompleted BOOLEAN NOT NULL DEFAULT false);");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace("Table sqllab.order_worker  was created");
			else
				logger.debug("Table sqllab.order_worker  was not created");
			stmt.close();
		} catch (SQLException e) {
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
			logger.trace("Send query \"DROP TABLE sqllab.order_worker ;\"");

			PreparedStatement stmt = dbConnector.prepareStatement("DROP TABLE sqllab.order_worker ;");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table sqllab.order_worker  was deleted");
			else
				logger.debug("table sqllab.order_worker  was not deleted");
			stmt.close();
		} catch (SQLException e) {
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
			logger.trace(
					"Send query \"INSERT INTO sqllab.order_worker  (order_id  ,worker_id, isCompleted)VALUES('AUDI','1234567890qwertyu',1);\"");

			PreparedStatement stmt = dbConnector.prepareStatement(""
					+ "INSERT INTO sqllab.order_worker  (order_id  ,worker_id, isCompleted)"
					+ "VALUES(?,?,false);");
			stmt.setInt(1, order_id);
			stmt.setInt(2, worker_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.order_worker  was created");
			else
				logger.debug("New sqllab.order_worker  was not created");
				stmt.close();
		} catch (SQLException e) {
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
			logger.trace(
					"Send query \"SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id FROM sqllab.customer WHERE first_name ='Alex' AND last_name ='Alkov');\"");
			// SELECT * FROM sqllab.car WHERE customer_id =(SELECT customer_id FROM
			// sqllab.customer WHERE first_name ='Alex' AND last_name ='Alkov');
			PreparedStatement stmt = dbConnector.prepareStatement(""
					+ "UPDATE sqllab.order_worker  "
					+ "SET isCompleted=? "
					+ "WHERE order_id=? AND worker_id=?;");
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
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
