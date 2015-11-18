package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.entity.Status;
import com.nixsolutions.util.CustomConnectionManager;

public class StatusDAOImpl implements StatusDAO {
	
	public StatusDAOImpl() {
		
	}

	@Override
	public Status create() throws SQLException {
		Status status = new Status();
		return createFrom(status);
	}

	public String getSelectByID() {
		return "SELECT * FROM sqllab.status WHERE status_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM sqllab.status;";
	}

	public String getUpdate() {
		return "UPDATE sqllab.status SET %1$s WHERE status_id = %2$s;";
	}

	public String getDelete() {
		return "DELETE FROM sqllab.status WHERE status_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO sqllab.status (status_name) VALUES (%1$s);";
	}

	public List<Status> parseResults(ResultSet rs) throws SQLException {
		List<Status> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				Status status = new Status();
				status.setId(rs.getInt("status_id"));
				status.setStatusName(rs.getString("status_name"));
				resultList.add(status);
			}
		} catch (Exception ex) {
			throw new SQLException(ex);
		}
		return resultList;
	}

	@Override
	public Status createFrom(Status entity) throws SQLException {
		Status entInstance = null;
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
			List<Status> resultList = parseResults(rs);
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
	public void update(Status entity) throws SQLException {
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
	public void delete(Status entity) throws SQLException {
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
	public Status getByPK(int id) throws SQLException {
		List<Status> resultList = null;
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
	public List<Status> getAll() throws SQLException {
		List<Status> resultList = null;
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
