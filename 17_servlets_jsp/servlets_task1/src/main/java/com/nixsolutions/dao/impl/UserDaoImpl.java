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
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;

public class UserDaoImpl implements UserDao{
	public static final Logger LOG = LogManager.getLogger();
	
	@Override
	public List<User> getAllUsers() {
		LOG.entry();
		String sql = "SELECT * FROM user;";
		List<User> users = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				String name = result.getString("user_name");
				String pswd = result.getString("user_password");
				Integer id = result.getInt("user_id");
				Integer role = result.getInt("role_id");

				users.add(new User(name, pswd, role, id));
			}
			LOG.trace("all the users were retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all users", e));
		}
		return LOG.exit(users);
	}
	
	@Override
	public boolean validateUserByNameAndPswd(String name, String pswd) {
		LOG.entry(name, pswd);
		String sql = "SELECT * FROM user WHERE user_name = ? AND user_password = ?;";
		boolean status = false;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, name);
			statem.setString(2, pswd);
			ResultSet result = statem.executeQuery();
			status = result.next();
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a user by name and password", e));
		}
		return LOG.exit(status);
	}
	
	@Override
	public User getUserByNameAndPswd(String name, String pswd) {
		LOG.entry(name, pswd);
		String sql = "SELECT * FROM user WHERE user_name = ? AND user_password = ?;";
		User user = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, name);
			statem.setString(2, pswd);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				Integer id = result.getInt("user_id");
				Integer role = result.getInt("role_id");
				user = new User(name, pswd, role, id);
			}
			LOG.trace("the user was retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a user by Id", e));
		}
		return LOG.exit(user);
	}
	
	@Override
	public User getUserById(int userId) {
		LOG.entry(userId);
		String sql = "SELECT * FROM user WHERE user_id = ?;";
		User user = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setInt(1, userId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				String name = result.getString("user_name");
				String pswd = result.getString("user_password");
				Integer id = result.getInt("user_id");
				Integer role = result.getInt("role_id");
				user = new User(name, pswd, role, id);
			}
			LOG.trace("the user was retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a user by Id", e));
		}
		return LOG.exit(user);
	}

	@Override
	public void createUser(User user) {
		LOG.entry(user);
		String sql = "INSERT INTO user (user_name, user_password, role_id) VALUES (?, ?, ?)";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, user.getUserName());
			statem.setString(2, user.getUserPassword());
			statem.setInt(3, user.getRoleId());
			statem.executeUpdate();
			LOG.exit("user was created");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create an author", e));
		}
	}

	@Override
	public void updateUser(User user) {
		LOG.entry(user);
		String sql = "UPDATE user SET user_name = ?, user_password = ?, role_id=?  WHERE user_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, user.getUserName());
			statem.setString(2, user.getUserPassword());
			statem.setInt(3, user.getRoleId());
			statem.setLong(4, user.getUserId());
			statem.executeUpdate();
			LOG.exit("user with id: " + user.getUserId() + " was updated");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to update the author", e));
		}
	}

	@Override
	public void deleteUser(User user) {
		LOG.entry(user);
		String sql = "DELETE FROM user WHERE user_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setLong(1, user.getUserId());
			statem.executeUpdate();
			LOG.exit("user with id: " + user.getUserId() + " was deleted");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to delete the author", e));
		}
	}

}
