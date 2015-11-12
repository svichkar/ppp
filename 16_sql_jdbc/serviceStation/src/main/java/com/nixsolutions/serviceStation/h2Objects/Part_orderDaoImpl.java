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

import com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao;
import com.nixsolutions.serviceStation.dbObjects.Part_order;

/**
 * @author mixeyes
 *
 */
public class Part_orderDaoImpl implements Part_orderDao {

	private final static Logger logger = LogManager.getLogger();
	private Connection dbConnector;

	public Part_orderDaoImpl(Connection dbConnector) {
		this.dbConnector = dbConnector;
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
			logger.trace("Send query \"CREATE TABLE part_order( " + "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), " + "part_id INT NOT NULL,"
					+ "FOREIGN KEY (part_id) REFERENCES  part(part_id), " + "amount TINYINT);\"");

			PreparedStatement stmt = dbConnector.prepareStatement("CREATE TABLE part_order( " + "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), " + "part_id INT NOT NULL,"
					+ "FOREIGN KEY (part_id) REFERENCES  part(part_id), " + "amount TINYINT);");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table sqllab.part_order was created");
			else
				logger.debug("Table sqllab.part_order was not created");
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
			logger.trace("Send query \"DROP TABLE part_order;\"");

			PreparedStatement stmt = dbConnector.prepareStatement("DROP TABLE part_order;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table sqllab.part_order was deleted");
			else
				logger.debug("table sqllab.part_order was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#
	 * getPartsByOrderId(java.lang.Integer)
	 */
	@Override
	public List<Part_order> getPartsByOrderId(Integer order_id) {
		List<Part_order> part_orders = new ArrayList<Part_order>();
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM sqllab.part_order WHERE order_id=?\"");

			PreparedStatement stmt = dbConnector.prepareStatement("SELECT * FROM sqllab.part_order WHERE order_id=?");
			stmt.setInt(1, order_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.part_order objects");
			while (set.next()) {
				part_orders.add(new Part_order(set.getInt("order_id"), set.getInt("part_id"), set.getInt("amount")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return part_orders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#setPartToOrder(
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void setPartToOrder(Integer order_id, Integer part_id, Integer amount) {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"INSERT INTO sqllab.car (model  ,vin_number, customer_id )VALUES('AUDI','1234567890qwertyu',1);\"");

			PreparedStatement stmt = dbConnector
					.prepareStatement("INSERT INTO sqllab.part_order (order_id ,part_id,amount)" + "VALUES(?,?,?)");
			stmt.setInt(1, order_id);
			stmt.setInt(2, part_id);
			stmt.setInt(3, amount);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New part_id was added to order");
			else
				logger.trace("New part_id was not added to order");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#
	 * deletePartFromOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void deletePartFromOrder(Integer order_id, Integer part_id) {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DELETE FROM sqllab.part_order WHERE order_id=?,part_id=?;\"");

			PreparedStatement stmt = dbConnector
					.prepareStatement("DELETE FROM sqllab.part_order WHERE order_id=?,part_id=?;");
			stmt.setInt(1, order_id);
			stmt.setInt(2, part_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("car was deleted");
			else
				logger.debug("car was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
