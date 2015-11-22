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

import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.util.CustomConnectionManager;

public class WorkerDAOImpl implements WorkerDAO{
	
	public static Logger LOG = LogManager.getLogger(WorkerDAOImpl.class.getName());
	
	public WorkerDAOImpl() {
		
	}

	@Override
	public Worker create() {
		Worker worker = new Worker();
		return createFrom(worker);
	}

	public String getSelectByID() {
		return "SELECT * FROM sqllab.worker WHERE worker_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM sqllab.worker;";
	}

	public String getUpdate() {
		return "UPDATE sqllab.worker SET %1$s WHERE worker_id = %2$s;";
	}

	public String getDelete() {
		return "DELETE FROM sqllab.worker WHERE worker_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO sqllab.worker (first_name, last_name, specialization_id, status_id) VALUES (%1$s);";
	}

	public List<Worker> parseResults(ResultSet rs) {
		List<Worker> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				Worker worker = new Worker();
				worker.setId(rs.getInt("worker_id"));
				worker.setFirstName(rs.getString("first_name"));
				worker.setLastName(rs.getString("last_name"));
				worker.setSpecializationId(rs.getInt("specialization_id"));
				worker.setStatusId(rs.getInt("status_id"));
				worker.setUserId(rs.getInt("user_id"));
				resultList.add(worker);
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		return resultList;
	}

	@Override
	public Worker createFrom(Worker entity) {
		Worker entInstance = null;
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
				List<Worker> resultList = parseResults(rs);
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
	public void update(Worker entity) {
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
	public void delete(Worker entity) {
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
	public Worker getByPK(int id) {
		List<Worker> resultList = null;
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
	public List<Worker> getAll() {
		List<Worker> resultList = null;
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
