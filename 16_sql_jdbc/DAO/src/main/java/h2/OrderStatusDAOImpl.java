package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.GenericDao;
import entities.OrderStatus;

public class OrderStatusDAOImpl implements GenericDao<OrderStatus> {

	private Connection conn;

	public OrderStatusDAOImpl(Connection conn) throws SQLException {
		this.conn = conn;
		//define schema
		PreparedStatement ps = conn.prepareStatement("SET SCHEMA SQLLAB");
		ps.execute();
	}

	private class ChOrderStatus extends OrderStatus {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	@Override
	public boolean create(OrderStatus t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO order_status (order_status_name) VALUES (?)");
		ps.setString(1, t.getOrder_status_name());

		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean update(OrderStatus t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("UPDATE order_status SET order_status_name=? WHERE order_status_id=?");
		ps.setString(1, t.getOrder_status_name());
		ps.setInt(2, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(OrderStatus t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("DELETE FROM order_status WHERE order_status_id=?");
		ps.setInt(1, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}

		return false;
	}

	@Override
	public OrderStatus findByPK(int id) throws SQLException {
		boolean resultExecution;
		ChOrderStatus orderstatus = new ChOrderStatus();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM order_status WHERE order_status_id=?");
		ps.setInt(1, id);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				orderstatus.setId(rs.getInt("order_status_id"));
				orderstatus.setOrder_status_name(rs
						.getString("order_status_name"));
			}
		} else {
			return null;
		}

		return orderstatus;
	}

	@Override
	public OrderStatus findByName(String name) throws SQLException {
		boolean resultExecution;
		ChOrderStatus orderstatus = new ChOrderStatus();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM order_status WHERE order_status_name=?");
		ps.setString(1, name);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				orderstatus.setId(rs.getInt("order_status_id"));
				orderstatus.setOrder_status_name(rs
						.getString("order_status_name"));
			}
		} else {
			return null;
		}

		return orderstatus;
	}

}
