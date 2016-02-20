package com.nixsolutions.studentgrade.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.studentgrade.dao.UserDAO;
import com.nixsolutions.studentgrade.entity.User;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class UserDAOImpl implements UserDAO {
	private static Logger LOG = LogManager.getLogger(UserDAOImpl.class.getName());

	@Override
	public void createUser(User user) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO user VALUES( ?, ?, ?, ?, ?)")) {
				ps.setLong(1, user.getUserId());
				ps.setString(2, user.getLogin());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getEmail());
				ps.setLong(5, user.getRoleId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public void updateUser(User user) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn
					.prepareStatement("UPDATE user SET login=?, password=?, email=?, role_id=? WHERE user_id = ?")) {
				ps.setString(1, user.getLogin());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				ps.setLong(4, user.getRoleId());
				ps.setLong(5, user.getUserId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public void deleteUser(User user) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE from user WHERE user_id = ?")) {
				ps.setLong(1, user.getUserId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public User findUserById(Long userId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE user_id = ?")) {
				ps.setLong(1, userId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new User(rs.getLong("user_id"), rs.getString("login"), rs.getString("password"),
						rs.getString("email"), rs.getLong("role_id"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public User findUserByLogin(String login) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE lower(login) = ?")) {
				ps.setString(1, login.toLowerCase());
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new User(rs.getLong("user_id"), rs.getString("login"), rs.getString("password"),
						rs.getString("email"), rs.getLong("role_id"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}
	
	@Override
	public User findUserByEmail(String email) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE lower(email) = ?")) {
				ps.setString(1, email.toLowerCase());
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new User(rs.getLong("user_id"), rs.getString("login"), rs.getString("password"),
						rs.getString("email"), rs.getLong("role_id"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<User> findAllUsers() {
		List<User> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					User user = new User();
					user.setUserId(rs.getLong("user_id"));
					user.setLogin(rs.getString("login"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setRoleId(rs.getLong("role_id"));
					result.add(user);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

}
