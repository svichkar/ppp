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

import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.util.CustomConnectionManager;

public class OrderInWorkDAOImpl implements OrderInWorkDAO{
	
	public static Logger LOG = LogManager.getLogger(OrderInWorkDAOImpl.class.getName());
	
	public OrderInWorkDAOImpl() {
		
	}

	@Override
	public OrderInWork create() {
		OrderInWork oiw = new OrderInWork();
		return createFrom(oiw);
	}

	public String getSelectByID() {
		return "SELECT * FROM sqllab.order_in_work WHERE order_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM sqllab.order_in_work;";
	}

	public String getUpdate() {
		return "UPDATE sqllab.order_in_work SET %1$s WHERE order_id = %2$s;";
	}

	public String getDelete() {
		return "DELETE FROM sqllab.order_in_work WHERE order_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO sqllab.order_in_work (order_status_id, description, car_id, timestamp_start, timestamp_finish) VALUES (%1$s);";
	}

	public List<OrderInWork> parseResults(ResultSet rs) throws SQLException {
		List<OrderInWork> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				OrderInWork oiw = new OrderInWork();
				oiw.setId(rs.getInt("order_id"));
				oiw.setOrderStatusId(rs.getInt("order_status_id"));
				oiw.setDescription(rs.getString("description"));
				oiw.setCarId(rs.getInt("car_id"));
				oiw.setTimestampStart(rs.getTimestamp("timestamp_start"));
				oiw.setTimestampFinish(rs.getTimestamp("timestamp_finish"));
				resultList.add(oiw);
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		return resultList;
	}

	@Override
	public OrderInWork createFrom(OrderInWork entity) {
		OrderInWork entInstance = null;
		try (Connection conn = CustomConnectionManager.getConnection()) {
			String sql = getCreate();
			int pk = 0;
			try (Statement stmt = conn.createStatement()) {
				int insertCount = stmt.executeUpdate(String.format(sql, entity.getValuesCommaSeparated()), 1);
				if (insertCount != 1) {
					throw new SQLException(
							"On creation either multiple or no records were affected. Count: " + insertCount);
				}
				ResultSet keySet = stmt.getGeneratedKeys();
				while (keySet.next()) {
					pk = keySet.getInt(1);
				}
			}
			sql = getSelectByID();
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, pk);
				ResultSet rs = stmt.executeQuery();
				List<OrderInWork> resultList = parseResults(rs);
				if (resultList == null || resultList.size() != 1) {
					throw new SQLException("No or multiple records found by id. ID: " + pk);
				}
				entInstance = resultList.get(0);
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
		return entInstance;
	}

	@Override
	public void update(OrderInWork entity) {
		String sql = getUpdate();
		try (Connection conn = CustomConnectionManager.getConnection()) {
			try (Statement stmt = conn.createStatement()) {
				int updCount = stmt.executeUpdate(String.format(sql, entity.toString(), entity.getId()));
				if (updCount != 1) {
					throw new SQLException("On update either multiple or no records were affected. Count: " + updCount);
				}
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
	}

	@Override
	public void delete(OrderInWork entity) {
		String sql = getDelete();
		try (Connection conn = CustomConnectionManager.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, entity.getId());
				int updCount = stmt.executeUpdate();
				if (updCount != 1) {
					throw new SQLException(
							"On deletion either multiple or no records were affected. Count: " + updCount);
				}
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
	}

	@Override
	public OrderInWork getByPK(int id) {
		List<OrderInWork> resultList = null;
		try (Connection conn = CustomConnectionManager.getConnection()) {
			String sql = getSelectByID();
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				resultList = parseResults(rs);
			}
			if (resultList == null || resultList.size() == 0) {
				return null;
			} else if (resultList.size() > 1) {
				throw new SQLException("More than one result by presumably unique id. ID: " + id);
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
		return resultList.get(0);
	}

	@Override
	public List<OrderInWork> getAll() {
		List<OrderInWork> resultList = null;
		String sql = getSelectAll();
		try (Connection conn = CustomConnectionManager.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				resultList = parseResults(rs);
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
		return resultList;
	}
}
