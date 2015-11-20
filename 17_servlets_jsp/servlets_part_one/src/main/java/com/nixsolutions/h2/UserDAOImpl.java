package com.nixsolutions.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.entities.PersistenceException;
import com.nixsolutions.entities.User;

public class UserDAOImpl extends AbstractH2DAO<User> {
	
	private Connection conn;

	public UserDAOImpl(Connection conn) {
		super(conn);
		this.conn = conn;
	}
	
	private class ChUser extends User {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	@Override
	public User create() throws PersistenceException {
		User user = new User();
		return createFrom(user);
	}

	@Override
	public String getSelectByID() {
		return "SELECT * FROM sqllab.user WHERE user_id = ?;";
	}

	@Override
	public String getSelectAll() {
		return "SELECT * FROM sqllab.user;";
	}

	@Override
	public String getUpdate() {
		return "UPDATE sqllab.user SET %1$s WHERE user_id = %2$s;";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM sqllab.user WHERE user_id = ?;";
	}

	@Override
	public String getCreate() {
		return "INSERT INTO sqllab.user (user_login, user_password, role_id) VALUES (%1$s);";
	}

	@Override
	public List<User> parseResults(ResultSet rs) throws PersistenceException {
		List<User> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChUser user = new ChUser();
				user.setId(rs.getInt("user_id"));
				user.setUserLogin(rs.getString("user_login"));
				user.setUserPassword(rs.getString("user_password"));
				user.setRoleId(rs.getInt("role_id"));
				resultList.add(user);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

	public String getSelectByLogin() {
		return "SELECT * FROM sqllab.user WHERE user_login = ?;";
	}
	
	public User getUserByLogin(String login) throws PersistenceException {
		List<User> resultList = null;
		String sql = getSelectByLogin();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else if (resultList.size() > 1) {
			throw new PersistenceException("More than one result by presumably unique login. Login: " + login);
		}
		return resultList.get(0);
	}
	
	public String getSelectAllUsersWithRole() {
		return "SELECT u.user_id, u.user_login, u.user_password, r.role_name FROM sqllab.user u" + 
				"INNER JOIN sqllab.role r ON u.role_id = r.role_id;";
	}
	
	public List<User> getUsersFullInfo() throws PersistenceException {
		List<User> resultList = null;
		String sql = getSelectAllUsersWithRole();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}
	
	public String getSelectUserWithRoleByPK() {
		return "SELECT u.user_id, u.user_login, u.user_password, r.role_name FROM sqllab.user u" + 
				"INNER JOIN sqllab.role r ON u.role_id = r.role_id WHERE user_id = ?;";
	}
	
	public User getUserFullInfo(int user_id) throws PersistenceException {
		List<User> resultList = null;
		String sql = getSelectByLogin();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else if (resultList.size() > 1) {
			throw new PersistenceException("More than one result by presumably unique ID. ID: " + user_id);
		}
		return resultList.get(0);
	}
}
