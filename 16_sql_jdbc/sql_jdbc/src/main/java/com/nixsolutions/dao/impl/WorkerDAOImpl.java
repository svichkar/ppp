package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.util.CustomConnectionManager;

public class WorkerDAOImpl implements WorkerDAO{
	
	public WorkerDAOImpl() {
		
	}

	@Override
	public Worker create() throws SQLException {
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

	public List<Worker> parseResults(ResultSet rs) throws SQLException {
		List<Worker> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				Worker worker = new Worker();
				worker.setId(rs.getInt("worker_id"));
				worker.setFirstName(rs.getString("first_name"));
				worker.setLastName(rs.getString("last_name"));
				worker.setSpecializationId(rs.getInt("specialization_id"));
				worker.setStatusId(rs.getInt("status_id"));
				resultList.add(worker);
			}
		} catch (Exception ex) {
			throw new SQLException(ex);
		}
		return resultList;
	}

	@Override
	public Worker createFrom(Worker entity) throws SQLException {
		Worker entInstance = null;
		Connection conn = CustomConnectionManager.getConnection();
		String sql = getCreate();
		int pk = 0;
		try (Statement stmt = conn.createStatement()) {
			int insertCount = stmt.executeUpdate(String.format(sql, entity.getValuesCommaSeparated()), 1);
			if (insertCount != 1) {
				throw new SQLException("On creation either multiple or no records were affected. Count: " + insertCount);
			}
			ResultSet keySet = stmt.getGeneratedKeys();
			while (keySet.next()) {
				pk = keySet.getInt(1);
			}
		} catch (SQLException ex) {
			conn.close();
			throw new SQLException(ex);
		}
		sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, pk);
			ResultSet rs = stmt.executeQuery();
			List<Worker> resultList = parseResults(rs);
			if (resultList == null || resultList.size() != 1) {
				throw new SQLException("No records found by id. ID: " + pk);
			}
			entInstance = resultList.get(0);
		} finally {
			conn.close();
		}
		return entInstance;
	}

	@Override
	public void update(Worker entity) throws SQLException {
		String sql = getUpdate();
		Connection conn = CustomConnectionManager.getConnection();
		try (Statement stmt = conn.createStatement()) {
			int updCount = stmt.executeUpdate(String.format(sql, entity.toString(), entity.getId()));
			if (updCount != 1) {
				throw new SQLException("On update either multiple or no records were affected. Count: " + updCount);
			}
		} finally {
			conn.close();
		}
	}

	@Override
	public void delete(Worker entity) throws SQLException {
		String sql = getDelete();
		Connection conn = CustomConnectionManager.getConnection();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, entity.getId());
			int updCount = stmt.executeUpdate();
			if (updCount != 1) {
				throw new SQLException("On deletion either multiple or no records were affected. Count: " + updCount);
			}
		} finally {
			conn.close();
		}
	}

	@Override
	public Worker getByPK(int id) throws SQLException {
		List<Worker> resultList = null;
		Connection conn = CustomConnectionManager.getConnection();
		String sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} finally {
			conn.close();
		}
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else if (resultList.size() > 1) {
			throw new SQLException("More than one result by presumably unique id. ID: " + id);
		}
		return resultList.get(0);
	}

	@Override
	public List<Worker> getAll() throws SQLException {
		List<Worker> resultList = null;
		String sql = getSelectAll();
		Connection conn = CustomConnectionManager.getConnection();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} finally {
			conn.close();
		}
		return resultList;
	}
}
