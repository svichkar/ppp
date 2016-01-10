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
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.entities.OrderWorker;

public class OrderWorkerDAOImpl implements OrderWorkerDAO<OrderWorker> {

	private final static Logger LOG = LogManager
			.getLogger(OrderWorkerDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public OrderWorkerDAOImpl() {
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
	public boolean create(OrderWorker t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("INSERT INTO order_worker (worker_id, order_id, completed) VALUES (?, ?, ?)");
			ps.setInt(1, t.getWorker_id());
			ps.setInt(2, t.getId());
			ps.setBoolean(3, t.getCompleted());
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
	public boolean update(OrderWorker t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("UPDATE order_worker SET completed=? WHERE worker_id=? AND order_id=?");
			ps.setBoolean(1, t.getCompleted());
			ps.setInt(2, t.getWorker_id());
			ps.setInt(3, t.getId());
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
	public boolean delete(OrderWorker t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("DELETE FROM order_worker WHERE worker_id=? AND order_id=?");
			ps.setInt(1, t.getWorker_id());
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
	public OrderWorker findByPK(int id) {
		return null;
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
	public List<OrderWorker> getAll() {
		boolean resultExecution;
		List<OrderWorker> lOW = new ArrayList<OrderWorker>();
		try {
			ps = conn.prepareStatement("SELECT * FROM order_worker");
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					OrderWorker ow = new OrderWorker();
					ow.setId(rs.getInt("order_id"));
					ow.setWorker_id(rs.getInt("worker_id"));
					lOW.add(ow);
				}
			} else {
				lOW = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}

		return lOW;
	}

}
