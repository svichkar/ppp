package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.GenericDao;
import entities.OrderInWork;

public class OrderInWorkDAOImpl implements GenericDao<OrderInWork> {

	private Connection conn;

	public OrderInWorkDAOImpl(Connection conn) throws SQLException {
		this.conn = conn;
		//define schema
		PreparedStatement ps = conn.prepareStatement("SET SCHEMA SQLLAB");
		ps.execute();
	}

	private class ChOrderInWork extends OrderInWork {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	@Override
	public boolean create(OrderInWork t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO order_in_work (order_status_id, description, car_id, datetime_start) VALUES (?, ?, ?, ?)");
		ps.setInt(1, t.getOrder_status_id());
		ps.setString(2, t.getDescription());
		ps.setInt(3, t.getCar_id());
		ps.setTimestamp(4, t.getDatetime_start());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(OrderInWork t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("UPDATE order_in_work SET description=?, datetime_start=?, "
						+ "datetime_end=?, car_id=?, order_status_id=? WHERE order_id=?");
		ps.setString(1, t.getDescription());
		ps.setTimestamp(2, t.getDatetime_start());
		;
		ps.setTimestamp(3, t.getDatetime_end());
		ps.setInt(4, t.getCar_id());
		ps.setInt(5, t.getOrder_status_id());
		ps.setInt(6, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(OrderInWork t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("DELETE FROM order_in_work WHERE order_id=?");
		ps.setInt(1, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public OrderInWork findByPK(int id) throws SQLException {
		boolean resultExecution;
		ChOrderInWork orderInWork = new ChOrderInWork();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM order_in_work WHERE order_id=?");
		ps.setInt(1, id);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				orderInWork.setId(rs.getInt("order_id"));
				orderInWork.setOrder_status_id(rs.getInt("order_status_id"));
				orderInWork.setDescription(rs.getString("description"));
				orderInWork
						.setDatetime_start(rs.getTimestamp("datetime_start"));
				orderInWork.setDatetime_end(rs.getTimestamp("datetime_end"));
				orderInWork.setCar_id(rs.getInt("car_id"));
			}
		} else {
			return null;
		}

		return orderInWork;
	}

	@Override
	public OrderInWork findByName(String name) throws SQLException {
			return null;
	}

}
