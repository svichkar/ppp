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

import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.H2ConnManager;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;

public class RoleDaoImpl implements RoleDao{
	public static final Logger LOG = LogManager.getLogger();
	
	@Override
	public List<Role> getAllRoles() {
		LOG.entry();
		String sql = "SELECT * FROM role;";
		List<Role> roles = new ArrayList<>();
		Connection conn = null;
		Statement statem = null;
		ResultSet result = null;
		try {
			conn = H2ConnManager.getConnection(); 
			statem = conn.createStatement();
			result = statem.executeQuery(sql);
			while (result.next()) {
				Role role = new Role(result.getString("role_name"));
				role.setRoleId(result.getInt("role_id"));
				roles.add(role);
			}
			LOG.trace("all the roles were retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all roles", e));
		}finally {
			H2ConnManager.closeQuitely(result);
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
		return LOG.exit(roles);
	}

	@Override
	public Role getRoleById(int roleId) {
		LOG.entry(roleId);
		String sql = "SELECT * FROM role WHERE role_id = ?;";
		Role role = null;
		Connection conn = null;
		PreparedStatement statem = null;
		ResultSet result = null;
		try {
			conn = H2ConnManager.getConnection(); 
			statem = conn.prepareStatement(sql);
			statem.setInt(1, roleId);
			result = statem.executeQuery();
			if (result.next()) {
				role = new Role(result.getString("role_name"));
				role.setRoleId(result.getInt("role_id"));
			}
			LOG.trace("the role was retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a role by Id", e));
		}finally {
			H2ConnManager.closeQuitely(result);
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
		return LOG.exit(role);
	}

	@Override
	public Role getRoleByName(String name) {
		LOG.entry(name);
		String sql = "SELECT * FROM role WHERE role_name = ?;";
		Role role = null;
		Connection conn = null;
		PreparedStatement statem = null;
		ResultSet result = null;
		try {
			conn = H2ConnManager.getConnection(); 
			statem = conn.prepareStatement(sql);
			statem.setString(1, name);
			result = statem.executeQuery();
			if (result.next()) {
				Integer roleId = result.getInt("role_id");
				String roleName = result.getString("role_name");
				role = new Role(roleName);
				role.setRoleId(roleId);
			}
			LOG.trace("the role was retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get the role by name", e));
		}finally {
			H2ConnManager.closeQuitely(result);
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
		return LOG.exit(role);
	}
	
	@Override
	public void createRole(Role role) {
		LOG.entry(role);
		String sql = "INSERT INTO role (role_name) VALUES (?)";
		Connection conn = null;
		PreparedStatement statem = null;
		try {
			conn = H2ConnManager.getConnection();
			statem = conn.prepareStatement(sql);
			statem.setString(1, role.getName());
			statem.executeUpdate();
			LOG.exit("role was created");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create a role", e));
		}finally {
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
	}

	@Override
	public void updateRole(Role role) {
		LOG.entry(role);
		String sql = "UPDATE role SET role_name = ? WHERE role_id = ?";
		Connection conn = null;
		PreparedStatement statem = null;
		try {
			conn = H2ConnManager.getConnection();
			statem = conn.prepareStatement(sql);
			statem.setString(1, role.getName());
			statem.setLong(2, role.getRoleId());
			statem.executeUpdate();
			LOG.exit("role with id: " + role.getRoleId() + " was updated");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to update the role", e));
		}finally {
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
	}

	@Override
	public void deleteRole(Role role) {
		LOG.entry(role);
		String sql = "DELETE FROM role WHERE role_id = ?";
		Connection conn = null;
		PreparedStatement statem = null;
		try {
			conn = H2ConnManager.getConnection();
			statem = conn.prepareStatement(sql);
			statem.setLong(1, role.getRoleId());
			statem.executeUpdate();
			LOG.exit("role with id: " + role.getRoleId() + " was deleted");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to delete the role", e));
		}finally {
			H2ConnManager.closeQuitely(statem);
			H2ConnManager.closeQuitely(conn);
			}
	}

}
