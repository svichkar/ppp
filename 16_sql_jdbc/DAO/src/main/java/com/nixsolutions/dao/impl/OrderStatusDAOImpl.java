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
import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.entities.OrderStatus;

public class OrderStatusDAOImpl implements OrderStatusDAO<OrderStatus> {

	private final static Logger LOG = LogManager
			.getLogger(OrderStatusDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public OrderStatusDAOImpl() {
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
	public boolean create(OrderStatus t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("INSERT INTO order_status (order_status_name) VALUES (?)");
			ps.setString(1, t.getOrder_status_name());
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
	public boolean update(OrderStatus t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("UPDATE order_status SET order_status_name=? WHERE order_status_id=?");
			ps.setString(1, t.getOrder_status_name());
			ps.setInt(2, t.getId());
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
	public boolean delete(OrderStatus t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("DELETE FROM order_status WHERE order_status_id=?");
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
	public OrderStatus findByPK(int id) {
		boolean resultExecution;
		OrderStatus orderstatus = new OrderStatus();
		try {
			ps = conn
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
				orderstatus = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}

		return orderstatus;
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
	public List<OrderStatus> getAll() {
		boolean resultExecution;
		List<OrderStatus> lOS = new ArrayList<OrderStatus>();
		try {
			ps = conn.prepareStatement("SELECT * FROM order_status");
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					OrderStatus orderstatus = new OrderStatus();
					orderstatus.setId(rs.getInt("order_status_id"));
					orderstatus.setOrder_status_name(rs
							.getString("order_status_name"));
					lOS.add(orderstatus);
				}
			} else {
				lOS = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}

		return lOS;
	}

}
