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
import com.nixsolutions.dao.GenericDao;
import com.nixsolutions.entities.WorkerStatusSpecification;

public class WorkerStatusSpecificationImpl implements GenericDao<WorkerStatusSpecification> {

	private final static Logger LOG = LogManager.getLogger(WorkerStatusSpecificationImpl.class);
	private Connection conn;
	private PreparedStatement ps;
	private List<WorkerStatusSpecification> listResults;

	public WorkerStatusSpecificationImpl() {
	
	}

	@Deprecated
	@Override
	public boolean create(WorkerStatusSpecification t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean update(WorkerStatusSpecification t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean delete(WorkerStatusSpecification t) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public WorkerStatusSpecification findByPK(int id) {
		boolean resultExecution;
		WorkerStatusSpecification wss = new WorkerStatusSpecification();
		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"SELECT * FROM worker w join worker_specification ws on w.spec_id=ws.spec_id join status s on w.status_id=s.status_id WHERE worker_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					wss.setId(rs.getInt("worker_id"));
					wss.setF_name(rs.getString("f_name"));
					wss.setL_name(rs.getString("l_name"));
					wss.setSpec_id(rs.getInt("spec_id"));
					wss.setSpec_name(rs.getString("spec_name"));
					wss.setStatus_id(rs.getInt("status_id"));
					wss.setStatus_name(rs.getString("status_name"));
				}
			} else {
				wss = null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		return wss;
	}

	@Override
	public List<WorkerStatusSpecification> getAll() {
		boolean resultExecution;
		listResults = new ArrayList<>();

		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"SELECT * FROM worker w join worker_specification ws on w.spec_id=ws.spec_id join status s on w.status_id=s.status_id");
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					WorkerStatusSpecification wss = new WorkerStatusSpecification();
					wss.setId(rs.getInt("worker_id"));
					wss.setF_name(rs.getString("f_name"));
					wss.setL_name(rs.getString("l_name"));
					wss.setSpec_id(rs.getInt("spec_id"));
					wss.setSpec_name(rs.getString("spec_name"));
					wss.setStatus_id(rs.getInt("status_id"));
					wss.setStatus_name(rs.getString("status_name"));
					listResults.add(wss);
				}
			}

		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		return listResults;
	}

}
