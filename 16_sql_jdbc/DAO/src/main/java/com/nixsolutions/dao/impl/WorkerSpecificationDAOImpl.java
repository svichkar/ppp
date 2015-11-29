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
import com.nixsolutions.dao.WorkerSpecificationDAO;
import com.nixsolutions.entities.WorkerSpecification;

public class WorkerSpecificationDAOImpl implements
		WorkerSpecificationDAO<WorkerSpecification> {

	private final static Logger LOG = LogManager
			.getLogger(WorkerSpecificationDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public WorkerSpecificationDAOImpl() {
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
	public boolean create(WorkerSpecification t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("INSERT INTO worker_specification (spec_name) VALUES (?)");
			ps.setString(1, t.getSpec_name());
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
	public boolean update(WorkerSpecification t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("UPDATE worker_specification SET spec_name=? WHERE spec_id=?");
			ps.setString(1, t.getSpec_name());
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
	public boolean delete(WorkerSpecification t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("DELETE FROM worker_specification WHERE spec_id=?");

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
	public WorkerSpecification findByPK(int id) {
		boolean resultExecution;
		WorkerSpecification ws = new WorkerSpecification();
		try {
			ps = conn
					.prepareStatement("SELECT * FROM worker_specification WHERE spec_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					ws.setId(rs.getInt("spec_id"));
					ws.setSpec_name(rs.getString("spec_name"));
				}
			} else {
				ws = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		return ws;
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
	public List<WorkerSpecification> getAll() {
		boolean resultExecution;
		List<WorkerSpecification> lWS = new ArrayList<WorkerSpecification>();
		try {
			ps = conn.prepareStatement("SELECT * FROM worker_specification");
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					WorkerSpecification ws = new WorkerSpecification();
					ws.setId(rs.getInt("spec_id"));
					ws.setSpec_name(rs.getString("spec_name"));
					lWS.add(ws);
				}
			} else {
				lWS = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}

		return lWS;
	}

}
