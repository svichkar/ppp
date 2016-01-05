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

import com.nixsolutions.app.H2ConnManager;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;

public class RoleDaoImpl implements RoleDao{
	public static final Logger LOG = LogManager.getLogger();
	
	@Override
	public List<Role> getAllRoles() {
		LOG.entry();
		String sql = "SELECT * FROM role;";
		List<Role> roles = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				Role role = new Role(result.getString("name"));
				role.setRoleId(result.getInt("role_id"));
				roles.add(role);
			}
			LOG.trace("all the roles were retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all roles", e));
		}
		return LOG.exit(roles);
	}

	@Override
	public Role getRoleById(int roleId) {
		LOG.entry(roleId);
		String sql = "SELECT * FROM role WHERE role_id = ?;";
		Role role = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, roleId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				role = new Role(result.getString("role_name"));
				role.setRoleId(result.getInt("role_id"));
			}
			LOG.trace("the role was retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a role by Id", e));
		}
		return LOG.exit(role);
	}

	@Override
	public void createRole(Role role) {
		LOG.entry(role);
		String sql = "INSERT INTO role (role_name) VALUES (?)";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, role.getName());
			statem.executeUpdate();
			LOG.exit("role was created");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create a role", e));
		}
	}

	@Override
	public void updateRole(Role role) {
		LOG.entry(role);
		String sql = "UPDATE role SET role_name = ? WHERE role_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, role.getName());
			statem.setLong(2, role.getRoleId());
			statem.executeUpdate();
			LOG.exit("role with id: " + role.getRoleId() + " was updated");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to update the role", e));
		}
	}

	@Override
	public void deleteRole(Role role) {
		LOG.entry(role);
		String sql = "DELETE FROM role WHERE role_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setLong(1, role.getRoleId());
			statem.executeUpdate();
			LOG.exit("role with id: " + role.getRoleId() + " was deleted");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to delete the role", e));
		}
	}

}
