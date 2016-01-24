package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.app.ConnectionManager;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.entities.User;

public class UserDAOImpl implements UserDAO<User> {

	private final static Logger LOG = LogManager.getLogger(UserDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public UserDAOImpl() {

	}

	@Override
	public boolean create(User t) {
		int executionResult = 0;
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"INSERT INTO user (username,password,session_id role_id, customer_id) VALUES (?,?,?,?,?)");
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getSession_id());
			ps.setInt(4, t.getRole_id());
			ps.setInt(5, t.getCustomer_id());
			executionResult = ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(User t) {
		int executionResult = 0;
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"UPDATE user SET username=?, password=?, role_id=?, customer_id=?, session_id=? WHERE user_id=?");
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setInt(3, t.getRole_id());
			ps.setInt(4, t.getCustomer_id());
			ps.setString(5, t.getSession_id());
			ps.setInt(6, t.getId());
			executionResult = ps.executeUpdate();

		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(User t) {
		int executionResult = 0;
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("DELETE FROM user WHERE user_id=?");
			ps.setInt(1, t.getId());
			executionResult = ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public User findByPK(int id) {
		boolean resultExecution;
		User user = new User();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("SELECT * FROM user WHERE user_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					user.setId(rs.getInt("user_id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setRole_id(rs.getInt("role_id"));
					user.setSession_id(rs.getString("session_id"));
					user.setCustomer_id(rs.getInt("customer_id"));
				}
			} else {
				user = null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}

		return user;
	}

	public User findByName(String username) {
		boolean resultExecution;
		User user = new User();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("SELECT * FROM user WHERE username=?");
			ps.setString(1, username);
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					user.setId(rs.getInt("user_id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setSession_id(rs.getString("session_id"));
					user.setRole_id(rs.getInt("role_id"));
					user.setCustomer_id(rs.getInt("customer_id"));
				}
			} else {
				user = null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}

		return user;
	}

	public User findByNameAndPassword(String username, String password) {
		boolean resultExecution;
		User user = new User();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema here
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					user.setId(rs.getInt("user_id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setSession_id(rs.getString("session_id"));
					user.setRole_id(rs.getInt("role_id"));
					user.setCustomer_id(rs.getInt("customer_id"));
				}
			} else {
				user = null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}

		return user;
	}

	@Override
	public List<User> getAll() {
		boolean resultExecution;
		List<User> lUsers = new ArrayList<User>();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("SELECT * FROM user");
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("user_id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setSession_id(rs.getString("session_id"));
					user.setRole_id(rs.getInt("role_id"));
					user.setCustomer_id(rs.getInt("customer_id"));
					lUsers.add(user);
				}
			} else {
				lUsers = null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}

		return lUsers;
	}

	public void Dispose() {

		if (this.ps != null) {
			try {
				this.ps.close();
			} catch (SQLException ex) {
				LOG.error(ex, ex);
			}
		}

		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException ex) {
				LOG.error(ex, ex);
			}
		}

	}

}
