package com.nixsolutions.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.util.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;

public class UserDaoImpl implements UserDao {
	private final static Logger LOG = LogManager.getLogger(TermDaoImpl.class.getName());

	public UserDaoImpl(){
	}
	public void create(String userName, String password, String email, int role_id) {
		PreparedStatement stm = null;
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
			String sql = "INSERT INTO user (user_name, password, email, role_id) VALUES (?, ?, ?, ?);";
			stm = conn.prepareStatement(sql);
			stm.setString(1, userName);
			stm.setString(2, password);
			stm.setString(3, email);
			stm.setInt(4, role_id);
			stm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			ConnectionManager.releaseConnection(conn);
		}
	}

	public void update(User user) {
		Connection conn = null;
		PreparedStatement stm = null;
		String sql = "UPDATE user SET user_name=?, password=?, email=?, role_id=? WHERE user_id=?;";
		try {
			conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, user.getUserName());
			stm.setString(2, user.getPassword());
			stm.setString(3, user.getEmail());
			stm.setInt(4, user.getRoleId());
			stm.setInt(5, user.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			ConnectionManager.releaseConnection(conn);
		}
	}

	public void delete(User user) {
		PreparedStatement stm = null;
		Connection conn = null;
		String sql = "DELETE FROM user WHERE user_id=?;";
		try {
			conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, user.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			ConnectionManager.releaseConnection(conn);
		}
	}

	public User getByUserId(int userId) {
		User user = null;
		Connection conn = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM user WHERE user_id = ?;";
		try {
			conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, userId);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"), rs.getString("email"), rs.getInt("role_id"));
			}else{
				return null;
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			ConnectionManager.releaseConnection(conn);
		}
		return user;
	}

	public User getByUserName(String userName) {
		User user = null;
		Connection conn = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM user WHERE user_name = ?;";
		try {
			conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, userName);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
			user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"), rs.getString("email"), rs.getInt("role_id"));
			}else{
				return null;
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			ConnectionManager.releaseConnection(conn);
		}
		return user;
	}

	public List<User> getAll() {
		List<User> toReturn = new ArrayList<User>();
		Statement stm = null;
		Connection conn = null;
		String sql = "SELECT * FROM user;";
		try {
			conn = ConnectionManager.getConnection();
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				toReturn.add( new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"), rs.getString("email"), rs.getInt("role_id")));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			ConnectionManager.releaseConnection(conn);
		}
		return toReturn;
	}
}