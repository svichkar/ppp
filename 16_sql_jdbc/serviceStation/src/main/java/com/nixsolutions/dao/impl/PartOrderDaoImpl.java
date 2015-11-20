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

import com.nixsolutions.dao.PartOrderDao;
import com.nixsolutions.entity.PartOrder;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class PartOrderDaoImpl implements PartOrderDao {

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
			String query = "CREATE TABLE sqllab.part_order( " + "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), " + "part_id INT NOT NULL,"
					+ "FOREIGN KEY (part_id) REFERENCES  part(part_id), " + "amount TINYINT);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table sqllab.part_order was created");
			else
				logger.debug("Table sqllab.part_order was not created");
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
			String query = "DROP TABLE sqllab.part_order;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table sqllab.part_order was deleted");
			else
				logger.debug("table sqllab.part_order was not deleted");
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
	 * com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#getAllParts()
	 */
	@Override
	public List<PartOrder> getAllParts() throws SQLException {
		List<PartOrder> part_orders = new ArrayList<PartOrder>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.part_order;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.part_order objects");
			while (set.next()) {
				part_orders.add(new PartOrder(set.getInt("order_id"), set.getInt("part_id"), set.getInt("amount")));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
		return part_orders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#
	 * getPartsByOrderId(java.lang.Integer)
	 */
	@Override
	public List<PartOrder> getPartsByOrderId(Integer order_id) throws SQLException {
		List<PartOrder> part_orders = new ArrayList<PartOrder>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.part_order WHERE order_id=?";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, order_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.part_order objects");
			while (set.next()) {
				part_orders.add(new PartOrder(set.getInt("order_id"), set.getInt("part_id"), set.getInt("amount")));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
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
	public void setPartToOrder(Integer order_id, Integer part_id, Integer amount) throws SQLException {
		List<PartOrder> part_orders = new ArrayList<PartOrder>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "INSERT INTO sqllab.part_order (order_id ,part_id,amount)" + "VALUES(?,?,?)";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, order_id);
			stmt.setInt(2, part_id);
			stmt.setInt(3, amount);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New part_id was added to order");
			else
				logger.trace("New part_id was not added to order");
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
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Part_orderDao#
	 * deletePartFromOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void deletePartFromOrder(Integer order_id, Integer part_id) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DELETE FROM sqllab.part_order WHERE order_id=? AND part_id=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, order_id);
			stmt.setInt(2, part_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("part was deleted");
			else
				logger.debug("part was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}
}
