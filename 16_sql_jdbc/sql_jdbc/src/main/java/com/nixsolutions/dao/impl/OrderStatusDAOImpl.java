package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.util.CustomConnectionManager;

public class OrderStatusDAOImpl implements OrderStatusDAO{
	
	public OrderStatusDAOImpl() {
		
	}

	@Override
	public OrderStatus create() throws SQLException {
		OrderStatus os = new OrderStatus();
		return createFrom(os);
	}

	public String getSelectByID() {
		return "SELECT * FROM sqllab.order_status WHERE order_status_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM sqllab.order_status;";
	}

	public String getUpdate() {
		return "UPDATE sqllab.order_status SET %1$s WHERE order_status_id = %2$s;";
	}

	public String getDelete() {
		return "DELETE FROM sqllab.order_status WHERE order_status_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO sqllab.order_status (order_status_name) VALUES (%1$s);";
	}

	public List<OrderStatus> parseResults(ResultSet rs) throws SQLException {
		List<OrderStatus> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				OrderStatus orderStatus = new OrderStatus();
				orderStatus.setId(rs.getInt("order_status_id"));
				orderStatus.setOrderStatusName(rs.getString("order_status_name"));
				resultList.add(orderStatus);
			}
		} catch (Exception ex) {
			throw new SQLException(ex);
		}
		return resultList;
	}

	@Override
	public OrderStatus createFrom(OrderStatus entity) throws SQLException {
		OrderStatus entInstance = null;
		Connection conn = CustomConnectionManager.getConnection();
		String sql = getCreate();
		int pk = 0;
		try (Statement stmt = conn.createStatement()) {
			int insertCount = stmt.executeUpdate(String.format(sql, entity.getValuesCommaSeparated()), 1);
			if (insertCount != 1) {
				throw new SQLException("On creation either multiple or no records were affected. Count: " + insertCount);
			}
			ResultSet keySet = stmt.getGeneratedKeys();
			while (keySet.next()) {
				pk = keySet.getInt(1);
			}
		} catch (SQLException ex) {
			conn.close();
			throw new SQLException(ex);
		}
		sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, pk);
			ResultSet rs = stmt.executeQuery();
			List<OrderStatus> resultList = parseResults(rs);
			if (resultList == null || resultList.size() != 1) {
				throw new SQLException("No records found by id. ID: " + pk);
			}
			entInstance = resultList.get(0);
		} finally {
			conn.close();
		}
		return entInstance;
	}

	@Override
	public void update(OrderStatus entity) throws SQLException {
		String sql = getUpdate();
		Connection conn = CustomConnectionManager.getConnection();
		try (Statement stmt = conn.createStatement()) {
			int updCount = stmt.executeUpdate(String.format(sql, entity.toString(), entity.getId()));
			if (updCount != 1) {
				throw new SQLException("On update either multiple or no records were affected. Count: " + updCount);
			}
		} finally {
			conn.close();
		}
	}

	@Override
	public void delete(OrderStatus entity) throws SQLException {
		String sql = getDelete();
		Connection conn = CustomConnectionManager.getConnection();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, entity.getId());
			int updCount = stmt.executeUpdate();
			if (updCount != 1) {
				throw new SQLException("On deletion either multiple or no records were affected. Count: " + updCount);
			}
		} finally {
			conn.close();
		}
	}

	@Override
	public OrderStatus getByPK(int id) throws SQLException {
		List<OrderStatus> resultList = null;
		Connection conn = CustomConnectionManager.getConnection();
		String sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} finally {
			conn.close();
		}
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else if (resultList.size() > 1) {
			throw new SQLException("More than one result by presumably unique id. ID: " + id);
		}
		return resultList.get(0);
	}

	@Override
	public List<OrderStatus> getAll() throws SQLException {
		List<OrderStatus> resultList = null;
		String sql = getSelectAll();
		Connection conn = CustomConnectionManager.getConnection();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} finally {
			conn.close();
		}
		return resultList;
	}

}
