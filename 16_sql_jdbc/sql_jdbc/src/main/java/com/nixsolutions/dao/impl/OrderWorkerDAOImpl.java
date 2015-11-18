package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.util.CustomConnectionManager;

public class OrderWorkerDAOImpl implements OrderWorkerDAO {

	public OrderWorkerDAOImpl() {
		
	}

	@Override
	public OrderWorker create() throws SQLException {
		//throw new UnsupportedOperationException();
		return null;
	}

	public String getSelectByID() {
		return "SELECT * FROM sqllab.order_worker WHERE order_id = ? AND worker_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM sqllab.order_worker;";
	}

	public String getUpdate() {
		return "UPDATE sqllab.order_worker SET %1$s WHERE order_id = %2$s AND worker_id = %3$s;";
	}

	public String getDelete() {
		return "DELETE FROM sqllab.order_worker WHERE order_id = ? AND worker_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO sqllab.order_worker VALUES (%1$s);";
	}

	public List<OrderWorker> parseResults(ResultSet rs) throws SQLException {
		List<OrderWorker> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				OrderWorker ow = new OrderWorker();
				ow.setId(rs.getInt("order_id"));
				ow.setWorkerId(rs.getInt("worker_id"));
				ow.setIsCompleted(rs.getString("is_completed"));
				resultList.add(ow);
			}
		} catch (Exception ex) {
			throw new SQLException(ex);
		}
		return resultList;
	}

	@Override
	public OrderWorker createFrom(OrderWorker entity) throws SQLException {
		OrderWorker entInstance = null;
		Connection conn = CustomConnectionManager.getConnection();
		conn.setAutoCommit(false);
		String sql = getCreate();
		try (Statement stmt = conn.createStatement()) {
			int insertCount = stmt.executeUpdate(String.format(sql, entity.getValuesCommaSeparated()));
			if (insertCount != 1) {
				conn.rollback();
				throw new SQLException("On creation either multiple or no records were affected. Count: " + insertCount);
			}
			conn.commit();
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} finally {
				conn.close();
			}
			throw new SQLException(ex);
		}
		sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, entity.getId());
			stmt.setInt(2, entity.getWorkerId());
			ResultSet rs = stmt.executeQuery();
			List<OrderWorker> resultList = parseResults(rs);
			if (resultList == null || resultList.size() != 1) {
				throw new SQLException("No records found by id. Order_ID: " + 
						entity.getId() + ". Worker_ID: " + entity.getWorkerId());
			}
			entInstance = resultList.get(0);
		} finally {
			conn.close();
		}
		return entInstance;
	}

	@Override
	public void update(OrderWorker entity) throws SQLException {
		Connection conn = CustomConnectionManager.getConnection();
		conn.setAutoCommit(false);
		String sql = getUpdate();
		try {
			Statement stmt = conn.createStatement();
			int updCount = stmt.executeUpdate(String.format(sql, 
					entity.toString(), entity.getId(), entity.getWorkerId()));
			if (updCount != 1) {
				conn.rollback();
				throw new SQLException("On update either multiple or no records were affected. Count: " + updCount);
			}
			conn.commit();
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} finally {
				conn.close();
			}
			throw ex;
		}
	}

	@Override
	public void delete(OrderWorker entity) throws SQLException {
		Connection conn = CustomConnectionManager.getConnection();
		conn.setAutoCommit(false);
		String sql = getDelete();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, entity.getId());
			stmt.setInt(2, entity.getWorkerId());
			int updCount = stmt.executeUpdate();
			if (updCount != 1) {
				conn.rollback();
				throw new SQLException("On deletion either multiple or no records were affected. Count: " + updCount);
			}
			conn.commit();
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} finally {
				conn.close();
			}
			throw ex;
		}
	}

	public OrderWorker getByPK(int orderId, int workerId) throws SQLException {
		Connection conn = CustomConnectionManager.getConnection();
		List<OrderWorker> resultList = null;
		String sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderId);
			stmt.setInt(2, workerId);
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} finally {
			conn.close();
		}
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else if (resultList.size() > 1) {
			throw new SQLException("More than one result by presumably unique id. Order_id: " + 
					orderId + ". Worker_id: " + workerId);
		}
		return resultList.get(0);
	}

	@Override
	public List<OrderWorker> getAll() throws SQLException {
		List<OrderWorker> resultList = null;
		Connection conn = CustomConnectionManager.getConnection();
		String sql = getSelectAll();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} finally {
			conn.close();
		}
		return resultList;
	}

	@Override
	public OrderWorker getByPK(int id) throws SQLException {
		//throw new UnsupportedOperationException();
		return null;
	}
}
