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

import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.entity.Role;
import com.nixsolutions.util.CustomConnectionManager;

public class RoleDAOImpl implements RoleDAO {
	
	public static Logger LOG = LogManager.getLogger(RoleDAOImpl.class.getName());
	
	public RoleDAOImpl() {
		
	}

	@Override
	public Role create() {
		Role role = new Role();
		return createFrom(role);
	}

	public String getSelectByID() {
		return "SELECT * FROM sqllab.role WHERE role_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM sqllab.role;";
	}

	public String getUpdate() {
		return "UPDATE sqllab.role SET %1$s WHERE role_id = %2$s;";
	}

	public String getDelete() {
		return "DELETE FROM sqllab.role WHERE role_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO sqllab.role (role_name) VALUES (%1$s);";
	}

	public List<Role> parseResults(ResultSet rs) {
		List<Role> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				resultList.add(role);
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		return resultList;
	}

	@Override
	public Role createFrom(Role entity) {
		Role entInstance = null;
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
				List<Role> resultList = parseResults(rs);
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
	public void update(Role entity) {
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
	public void delete(Role entity) {
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
	public Role getByPK(int id) {
		List<Role> resultList = null;
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
	public List<Role> getAll() {
		List<Role> resultList = null;
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
