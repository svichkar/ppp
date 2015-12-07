package database.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.dao.UserDao;
import database.entities.User;

public class UserDaoImplements implements UserDao {
	private final static Logger LOG = LogManager.getLogger(TermDaoImplements.class.getName());
	private Connection conn;

	public UserDaoImplements(Connection connection) {
		conn = connection;
	}
	public void create(String userName, String password, int role_id) {
		PreparedStatement stm = null;
		try {
			String sql = "INSERT INTO user (user_name, password, role_id) VALUES (?, ?, ?);";
			stm = conn.prepareStatement(sql);
			stm.setString(1, userName);
			stm.setString(2, password);
			stm.setInt(3, role_id);
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
		}
	}

	public void update(User user) {
		PreparedStatement stm = null;
		String sql = "UPDATE user SET user_name=?, password=?, role_id=? WHERE user_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(4, user.getId());
			stm.setString(1, user.getUserName());
			stm.setString(2, user.getPassword());
			stm.setInt(3, user.getId());
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
		}
	}

	public void delete(User user) {
		PreparedStatement stm = null;
		String sql = "DELETE FROM user WHERE user_id=?;";
		try {
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
		}
	}

	public User getByUserId(int userId) {
		User user = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM user WHERE user_id = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, userId);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"), rs.getInt("role_id"));
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
		}
		return user;
	}

	public User getByUserName(String userName) {
		User user = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM user WHERE user_name = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, userName);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
			user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"), rs.getInt("role_id"));
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
		}
		return user;
	}

	public List<User> getAll() {
		List<User> toReturn = new ArrayList<User>();
		Statement stm = null;
		String sql = "SELECT * FROM user;";
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				toReturn.add( new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"), rs.getInt("role_id")));
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
		}
		return toReturn;
	}
	@Override
	public boolean checkUser(String userName, String password) {
		boolean result = false;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, userName);
			stm.setString(2, password);
			ResultSet rs = stm.executeQuery();
			result = rs.next();
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
		}
		return result;
	}

}
