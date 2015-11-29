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
import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.entities.Status;

public class StatusDAOImpl implements StatusDAO<Status> {

	private final static Logger LOG = LogManager.getLogger(StatusDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public StatusDAOImpl() {
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
	public boolean create(Status t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("INSERT INTO status (status_name) VALUES (?)");
			ps.setString(1, t.getStatus_name());
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
	public boolean update(Status t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("UPDATE status SET status_name=?  WHERE status_id=?");
			ps.setString(1, t.getStatus_name());
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
	public boolean delete(Status t) {
		int executionResult = 0;
		try {
			ps = conn.prepareStatement("DELETE FROM status WHERE status_id=?");
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
	public Status findByPK(int id) {
		boolean resultExecution;
		Status status = new Status();
		try {
			ps = conn
					.prepareStatement("SELECT * FROM status WHERE status_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					status.setId(rs.getInt("status_id"));
					status.setStatus_name(rs.getString("status_name"));
				}
			} else {
				status = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		return status;
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
	public List<Status> getAll() {
		boolean resultExecution;
		List<Status> lStatus = new ArrayList<Status>();
		try {
			ps = conn.prepareStatement("SELECT * FROM status");
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					Status status = new Status();
					status.setId(rs.getInt("status_id"));
					status.setStatus_name(rs.getString("status_name"));
					lStatus.add(status);
				}
			} else {
				lStatus = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}

		return lStatus;
	}
}
