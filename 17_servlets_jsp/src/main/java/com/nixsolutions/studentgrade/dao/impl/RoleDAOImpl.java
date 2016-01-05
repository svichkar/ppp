package com.nixsolutions.studentgrade.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.studentgrade.dao.RoleDAO;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class RoleDAOImpl implements RoleDAO {
	private static Logger LOG = LogManager.getLogger(RoleDAOImpl.class.getName());

	@Override
	public Role createRole(int roleId, String roleName) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO role VALUES( ?, ?)")) {
				ps.setInt(1, roleId);
				ps.setString(2, roleName);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return new Role(roleId, roleName);
	}

	@Override
	public void updateRole(Role role) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("UPDATE role SET role_name = ? WHERE role_id = ?")) {
				ps.setString(1, role.getRoleName());
				ps.setInt(2, role.getRoleId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public void deleteRole(Role role) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE from role WHERE role_id = ?")) {
				ps.setInt(1, role.getRoleId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public Role findRoleById(int roleId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM role WHERE role_id = ?")) {
				ps.setInt(1, roleId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Role(rs.getInt("role_id"), rs.getString("role_name"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public Role findRoleByName(String roleName) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM role WHERE lower(role_name) = ?")) {
				ps.setString(1, roleName.toLowerCase());
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Role(rs.getInt("role_id"), rs.getString("role_name"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Role> findAllRoles() {
		List<Role> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM role")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					Role role = new Role();
					role.setRoleId(rs.getInt("role_id"));
					role.setRoleName(rs.getString("role_name"));
					result.add(role);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

}
