package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.entity.OrderPart;
import com.nixsolutions.util.CustomConnectionManager;

public class OrderPartDAOImpl implements OrderPartDAO {

	public static Logger LOG = LogManager.getLogger(OrderPartDAOImpl.class.getName());

	public OrderPartDAOImpl() {

	}

	public String getSelectByID() {
		return "SELECT * FROM sqllab.order_part WHERE order_id = ? AND part_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM sqllab.order_part;";
	}

	public String getUpdate() {
		return "UPDATE sqllab.order_part SET %1$s WHERE order_id = %2$s AND part_id = %3$s;";
	}

	public String getDelete() {
		return "DELETE FROM sqllab.order_part WHERE order_id = ? AND part_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO sqllab.order_part VALUES (%1$s);";
	}

	public List<OrderPart> parseResults(ResultSet rs) {
		List<OrderPart> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				OrderPart op = new OrderPart();
				op.setId(rs.getInt("order_id"));
				op.setPartId(rs.getInt("part_id"));
				op.setUsedAmount(rs.getInt("used_amount"));
				resultList.add(op);
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		return resultList;
	}

	@Override
	public OrderPart create() {
		// throw new UnsupportedOperationException();
		return null;
	}

	@Override
	public OrderPart createFrom(OrderPart entity) {
		OrderPart entInstance = null;
		try (Connection conn = CustomConnectionManager.getConnection()) {
			conn.setAutoCommit(false);
			String sql = getCreate();
			try (Statement stmt = conn.createStatement()) {
				int insertCount = stmt.executeUpdate(String.format(sql, entity.getValuesCommaSeparated()));
				if (insertCount != 1) {
					throw new SQLException(
							"On creation either multiple or no records were affected. Count: " + insertCount);
				}
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
			sql = getSelectByID();
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, entity.getId());
				stmt.setInt(2, entity.getPartId());
				ResultSet rs = stmt.executeQuery();
				List<OrderPart> resultList = parseResults(rs);
				if (resultList == null || resultList.size() != 1) {
					throw new SQLException(
							"No records found by id. Order_ID: " + entity.getId() + ". Part_ID: " + entity.getPartId());
				}
				entInstance = resultList.get(0);
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
		return entInstance;
	}

	@Override
	public void update(OrderPart entity) {
		try (Connection conn = CustomConnectionManager.getConnection()) {
			conn.setAutoCommit(false);
			String sql = getUpdate();
			try (Statement stmt = conn.createStatement()) {
				int updCount = stmt
						.executeUpdate(String.format(sql, entity.toString(), entity.getId(), entity.getPartId()));
				if (updCount != 1) {
					throw new SQLException("On update either multiple or no records were affected. Count: " + updCount);
				}
				conn.commit();
			} catch (SQLException ex) {
				conn.rollback();
				throw ex;
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
	}

	@Override
	public void delete(OrderPart entity) {
		try (Connection conn = CustomConnectionManager.getConnection()) {
			conn.setAutoCommit(false);
			String sql = getDelete();
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, entity.getId());
				stmt.setInt(2, entity.getPartId());
				int updCount = stmt.executeUpdate();
				if (updCount != 1) {
					throw new SQLException(
							"On deletion either multiple or no records were affected. Count: " + updCount);
				}
				conn.commit();
			} catch (SQLException ex) {
				conn.rollback();
				throw ex;
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
	}

	public OrderPart getByPK(int orderId, int partId) {
		List<OrderPart> resultList = null;
		try (Connection conn = CustomConnectionManager.getConnection()) {
			String sql = getSelectByID();
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, orderId);
				stmt.setInt(2, partId);
				ResultSet rs = stmt.executeQuery();
				resultList = parseResults(rs);
			}
			if (resultList == null || resultList.size() == 0) {
				return null;
			} else if (resultList.size() > 1) {
				throw new SQLException(
						"More than one result by presumably unique id. Order_id: " + orderId + ". Part_id: " + partId);
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
		return resultList.get(0);
	}

	@Override
	public OrderPart getByPK(int id) {
		// throw new UnsupportedOperationException();
		return null;
	}

	@Override
	public List<OrderPart> getAll() {
		List<OrderPart> resultList = null;
		try (Connection conn = CustomConnectionManager.getConnection()) {
			String sql = getSelectAll();
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				resultList = parseResults(rs);
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
		return resultList;
	}

	public String getSelectByOrderId() {
		return "SELECT * FROM sqllab.order_part WHERE order_id = ?;";
	}
	
	@Override
	public List<OrderPart> getByOrderId(int orderId) {
		List<OrderPart> resultList = null;
		try (Connection conn = CustomConnectionManager.getConnection()) {
			String sql = getSelectByOrderId();
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, orderId);
				ResultSet rs = stmt.executeQuery();
				resultList = parseResults(rs);
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
		return resultList;
	}
}
