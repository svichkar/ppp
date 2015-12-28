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

	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "CREATE TABLE sqllab.part_order( " + "order_id INT NOT NULL,"
					+ "FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id), " + "part_id INT NOT NULL,"
					+ "FOREIGN KEY (part_id) REFERENCES  part(part_id), " + "amount TINYINT);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("Table sqllab.part_order was created");
			else
				LOGGER.debug("Table sqllab.part_order was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DROP TABLE sqllab.part_order;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace(" table sqllab.part_order was deleted");
			else
				LOGGER.debug("table sqllab.part_order was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public List<PartOrder> getAllParts() {
		List<PartOrder> part_orders = new ArrayList<PartOrder>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.part_order;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.part_order objects");
			while (set.next()) {
				part_orders.add(new PartOrder(set.getInt("order_id"), set.getInt("part_id"), set.getInt("amount")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return part_orders;
	}

	@Override
	public List<PartOrder> getPartsByOrderId(Integer orderId) {
		List<PartOrder> part_orders = new ArrayList<PartOrder>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.part_order WHERE orderId=?";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, orderId);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.part_order objects");
			while (set.next()) {
				part_orders.add(new PartOrder(set.getInt("order_id"), set.getInt("part_id"), set.getInt("amount")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return part_orders;
	}

	@Override
	public void setPartToOrder(Integer orderId, Integer part_id, Integer amount) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "INSERT INTO sqllab.part_order (order_id ,part_id,amount)" + "VALUES(?,?,?)";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, orderId);
			stmt.setInt(2, part_id);
			stmt.setInt(3, amount);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New part_id was added to order");
			else
				LOGGER.trace("New part_id was not added to order");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deletePartFromOrder(Integer orderId, Integer part_id) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DELETE FROM sqllab.part_order WHERE orderId=? AND part_id=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, orderId);
			stmt.setInt(2, part_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("part was deleted");
			else
				LOGGER.debug("part was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
