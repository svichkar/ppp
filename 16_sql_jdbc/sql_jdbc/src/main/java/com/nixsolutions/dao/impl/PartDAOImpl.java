package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.entity.Part;
import com.nixsolutions.util.CustomConnectionManager;

public class PartDAOImpl implements PartDAO {
	
	public PartDAOImpl() {
		
	}

	@Override
	public Part create() throws SQLException {
		Part part = new Part();
		return createFrom(part);
	}

	public String getSelectByID() {
		return "SELECT * FROM sqllab.part WHERE part_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM sqllab.part;";
	}

	public String getUpdate() {
		return "UPDATE sqllab.part SET %1$s WHERE part_id = %2$s;";
	}

	public String getDelete() {
		return "DELETE FROM sqllab.part WHERE part_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO sqllab.part (part_name, vendor, amount) VALUES (%1$s);";
	}

	public List<Part> parseResults(ResultSet rs) throws SQLException {
		List<Part> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				Part part = new Part();
				part.setId(rs.getInt("part_id"));
				part.setPartName(rs.getString("part_name"));
				part.setVendor(rs.getString("vendor"));
				part.setAmount(rs.getLong("amount"));
				resultList.add(part);
			}
		} catch (Exception ex) {
			throw new SQLException(ex);
		}
		return resultList;
	}

	@Override
	public Part createFrom(Part entity) throws SQLException {
		Part entInstance = null;
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
			List<Part> resultList = parseResults(rs);
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
	public void update(Part entity) throws SQLException {
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
	public void delete(Part entity) throws SQLException {
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
	public Part getByPK(int id) throws SQLException {
		List<Part> resultList = null;
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
	public List<Part> getAll() throws SQLException {
		List<Part> resultList = null;
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
