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
import com.nixsolutions.dao.AllWorkersInOrderDAO;
import com.nixsolutions.entities.AllWorkersInOrder;

public class AllWorkersInOrderDAOImpl implements AllWorkersInOrderDAO<AllWorkersInOrder> {

	private final static Logger LOG = LogManager.getLogger(AllWorkersInOrderDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;
	private List<AllWorkersInOrder> listResults;

	public AllWorkersInOrderDAOImpl() {

	}

	@Deprecated
	@Override
	public boolean create(AllWorkersInOrder t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean update(AllWorkersInOrder t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean delete(AllWorkersInOrder t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AllWorkersInOrder findByPK(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AllWorkersInOrder> getAll() {
		boolean resultExecution;
		listResults = new ArrayList<>();
		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("SELECT * FROM worker w JOIN order_worker wo ON w.worker_id=wo.worker_id");
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					AllWorkersInOrder allWorkers = new AllWorkersInOrder();
					allWorkers.setId(rs.getInt("order_id"));
					allWorkers.setWorker_id(rs.getInt("worker_id"));
					allWorkers.setF_name(rs.getString("f_name"));
					allWorkers.setL_name(rs.getString("l_name"));
					allWorkers.setCompleted(rs.getBoolean("completed"));
					listResults.add(allWorkers);
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

	public List<AllWorkersInOrder> getAll(int order_id) {
		boolean resultExecution;
		listResults = new ArrayList<>();
		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();

			ps = conn.prepareStatement(
					"SELECT * FROM worker w JOIN order_worker wo ON w.worker_id=wo.worker_id WHERE wo.order_id=?");
			ps.setInt(1, order_id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					AllWorkersInOrder allWorkers = new AllWorkersInOrder();
					allWorkers.setId(rs.getInt("order_id"));
					allWorkers.setWorker_id(rs.getInt("worker_id"));
					allWorkers.setF_name(rs.getString("f_name"));
					allWorkers.setL_name(rs.getString("l_name"));
					allWorkers.setCompleted(rs.getBoolean("completed"));
					listResults.add(allWorkers);
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

	public AllWorkersInOrder findByOrderAndWorker(int order_id, int worker_id) {
		boolean resultExecution;
		AllWorkersInOrder allWorker = new AllWorkersInOrder();
		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"SELECT * FROM worker w JOIN order_worker wo ON w.worker_id=wo.worker_id WHERE wo.order_id=? AND w.worker_id=?");
			ps.setInt(1, order_id);
			ps.setInt(2, worker_id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					allWorker.setId(rs.getInt("order_id"));
					allWorker.setWorker_id(rs.getInt("worker_id"));
					allWorker.setF_name(rs.getString("f_name"));
					allWorker.setL_name(rs.getString("l_name"));
					allWorker.setCompleted(rs.getBoolean("completed"));
				}
			} else {
				allWorker = null;
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
		return allWorker;
	}

}
