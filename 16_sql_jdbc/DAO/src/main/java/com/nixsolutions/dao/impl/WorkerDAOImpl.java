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
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.entities.Worker;

public class WorkerDAOImpl implements WorkerDAO<Worker> {

	private final static Logger LOG = LogManager.getLogger(WorkerDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public WorkerDAOImpl() {
		try {
			conn = ConnectionManager.getConnection();
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
	public boolean create(Worker t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES (?, ?, ?, ?)");
			ps.setString(1, t.getF_name());
			ps.setString(2, t.getL_name());
			ps.setInt(3, t.getSpec_id());
			ps.setInt(4, t.getStatus_id());
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
	public boolean update(Worker t) {
		int executionResult = 0;
		try {

			ps = conn
					.prepareStatement("UPDATE worker SET f_name=?,l_name=?, spec_id=?, status_id=? WHERE worker_id=?");
			ps.setString(1, t.getF_name());
			ps.setString(2, t.getL_name());
			ps.setInt(3, t.getSpec_id());
			ps.setInt(4, t.getStatus_id());
			ps.setInt(5, t.getId());
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
	public boolean delete(Worker t) {
		int executionResult = 0;
		try {
			ps = conn.prepareStatement("DELETE FROM worker WHERE worker_id=?");
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
	public Worker findByPK(int id) {
		boolean resultExecution;
		Worker worker = new Worker();
		try {
			ps = conn
					.prepareStatement("SELECT * FROM worker WHERE worker_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					worker.setId(rs.getInt("worker_id"));
					worker.setF_name(rs.getString("f_name"));
					worker.setL_name(rs.getString("l_name"));
					worker.setSpec_id(rs.getInt("spec_id"));
					worker.setStatus_id(rs.getInt("status_id"));
				}
			} else {
				worker = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		return worker;
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
	public List<Worker> getAll() {
		boolean resultExecution;
		List<Worker> lWorkers = new ArrayList<Worker>();
		try {
			ps = conn.prepareStatement("SELECT * FROM worker");
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					Worker worker = new Worker();
					worker.setId(rs.getInt("worker_id"));
					worker.setF_name(rs.getString("f_name"));
					worker.setL_name(rs.getString("l_name"));
					worker.setSpec_id(rs.getInt("spec_id"));
					worker.setStatus_id(rs.getInt("status_id"));
					lWorkers.add(worker);
				}
			} else {
				lWorkers = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}

		return lWorkers;
	}

}
