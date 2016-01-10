package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.app.ConnectionManager;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.entities.OrderInWork;

public class OrderInWorkDAOImpl implements OrderInWorkDAO<OrderInWork> {

	private final static Logger LOG = LogManager
			.getLogger(OrderInWorkDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public OrderInWorkDAOImpl() {
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		} catch (ClassNotFoundException ex) {
			LOG.error(ex, ex);
		}

	}

	@Override
	public boolean create(OrderInWork t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("INSERT INTO order_in_work (order_status_id, description, car_id, datetime_start) VALUES (?, ?, ?, ?)");
			ps.setInt(1, t.getOrder_status_id());
			ps.setString(2, t.getDescription());
			ps.setInt(3, t.getCar_id());
			ps.setTimestamp(4, t.getDatetime_start());
			executionResult = ps.executeUpdate();

		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(OrderInWork t) {
		int executionResult = 0;
		try {
			ps = conn
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
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(OrderInWork t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("DELETE FROM order_in_work WHERE order_id=?");
			ps.setInt(1, t.getId());
			executionResult = ps.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public OrderInWork findByPK(int id) {
		boolean resultExecution;
		OrderInWork orderInWork = new OrderInWork();
		try {
			ps = conn
					.prepareStatement("SELECT * FROM order_in_work WHERE order_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					orderInWork.setId(rs.getInt("order_id"));
					orderInWork
							.setOrder_status_id(rs.getInt("order_status_id"));
					orderInWork.setDescription(rs.getString("description"));
					orderInWork.setDatetime_start(rs
							.getTimestamp("datetime_start"));
					orderInWork
							.setDatetime_end(rs.getTimestamp("datetime_end"));
					orderInWork.setCar_id(rs.getInt("car_id"));
				}
			} else {
				orderInWork = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}

		return orderInWork;
	}

	public void Dispose() {

		if (this.ps != null) {
			try {
				this.ps.close();
			} catch (SQLException ex) {
				LOG.error(ex, ex);
			}
		}

		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException ex) {
				LOG.error(ex, ex);
			}
		}

	}

	@Override
	public List<OrderInWork> getAll() {
		boolean resultExecution;
		List<OrderInWork> lOIW = new ArrayList<OrderInWork>();
		try {
			ps = conn.prepareStatement("SELECT * FROM order_in_work");
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					OrderInWork orderInWork = new OrderInWork();
					orderInWork.setId(rs.getInt("order_id"));
					orderInWork
							.setOrder_status_id(rs.getInt("order_status_id"));
					orderInWork.setDescription(rs.getString("description"));
					orderInWork.setDatetime_start(rs
							.getTimestamp("datetime_start"));
					orderInWork
							.setDatetime_end(rs.getTimestamp("datetime_end"));
					orderInWork.setCar_id(rs.getInt("car_id"));
					lOIW.add(orderInWork);
				}
			} else {
				lOIW = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}

		return lOIW;
	}

}
