/**
 * 
 */
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

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author ������
 *
 */
public class UserDaoImpl implements UserDao {

	private final static Logger logger = LogManager.getLogger();
	private User user;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DELETE FROM sqllab.worker_status WHERE worker_status_name=?";
			logger.trace("Send query \"" + query + "\"");

			Statement stmt = connection.createStatement();
			stmt.execute(
					"CREATE TABLE sqllab.users ( user_id INT IDENTITY, user_login VARCHAR(128) NOT NULL UNIQUE, user_password VARCHAR(17) NOT NULL);");
			stmt.execute("ALTER TABLE sqllab.users " + "ADD COLUMN user_role_id INT NOT NULL;");
			stmt.execute("ALTER TABLE sqllab.users "
					+ "ADD FOREIGN KEY(user_role_id ) REFERENCES sqllab.user_role (user_role_id );");
			connection.commit();
			logger.trace("Table sqllab.users was created");
			stmt.close();
			connection.setAutoCommit(true);
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData(
	 * )
	 */
	@Override
	public void deleteTableWithAllData() {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DROP TABLE sqllab.users ;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table sqllab.users was deleted");
			else
				logger.debug("table sqllab.users was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData(
	 * )
	 */
	@Override
	public void deleteUserByID(Integer user_id) {
		try (Connection connection = ConnectionManager.getConnection()){
			logger.debug("Create DB connector");
			String query = "DELETE FROM sqllab.users WHERE user_id=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, user_id);
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace("user "+user_id +" was deleted");
			else
				logger.debug("user "+user_id +" was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} 
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.UsersDao#validate(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public boolean validate(String login, String password) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.user WHERE user_login=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, login);
			ResultSet set = stmt.executeQuery();

			logger.trace("Generate the user object for " + login);
			user = new User();
			while (set.next()) {
				user.setUser_id(set.getInt("user_id"));
				user.setUser_login(set.getString("user_login"));
				user.setUser_password(set.getString("user_password"));
				user.setUser_role_id(set.getInt("user_role_id"));
			}
			stmt.close();
			if (password.equals(user.getUser_password())) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);

		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.UserDao#getAllCustomers()
	 */
	@Override
	public List<User> getAllCustomers() {
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.user WHERE  user_role_id=2;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.user objects");
			while (set.next()) {
				User qwerty = new User();
				qwerty.setUser_id(set.getInt("user_id"));
				qwerty.setUser_login(set.getString("user_login"));
				qwerty.setUser_password(set.getString("user_password"));
				qwerty.setUser_role_id(set.getInt("user_role_id"));
				users.add(qwerty);
			}
			stmt.close();
			return users;
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.UserDao#getAllWorkers()
	 */
	@Override
	public List<User> getAllWorkers() {
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.user WHERE  user_role_id!=2;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.user objects");
			while (set.next()) {
				User qwerty = new User();
				qwerty.setUser_id(set.getInt("user_id"));
				qwerty.setUser_login(set.getString("user_login"));
				qwerty.setUser_password(set.getString("user_password"));
				qwerty.setUser_role_id(set.getInt("user_role_id"));
				users.add(qwerty);
			}
			stmt.close();
			return users;
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.UserDao#getUserByID(java.lang.Integer)
	 */
	@Override
	public User getUserByID(Integer user_id) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.user WHERE user_id=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, user_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.user objects");
			while (set.next()) {
				User qwerty = new User();
				qwerty.setUser_id(set.getInt("user_id"));
				qwerty.setUser_login(set.getString("user_login"));
				qwerty.setUser_password(set.getString("user_password"));
				qwerty.setUser_role_id(set.getInt("user_role_id"));
				user = qwerty;
				return qwerty;
			}
			stmt.close();

		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.UserDao#getUserByID(java.lang.Integer)
	 */
	@Override
	public User getUserByLogin(String login) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.user WHERE user_login=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, login);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.user objects");
			while (set.next()) {
				User qwerty = new User();
				qwerty.setUser_id(set.getInt("user_id"));
				qwerty.setUser_login(set.getString("user_login"));
				qwerty.setUser_password(set.getString("user_password"));
				qwerty.setUser_role_id(set.getInt("user_role_id"));
				user = qwerty;
				return qwerty;
			}
			stmt.close();

		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.UserDao#updateUser(com.nixsolutions.entity.User)
	 */
	@Override
	public void updateUser(User user) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "UPDATE sqllab.user SET user_password =?, user_role_id =? WHERE user_id =?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, user.getUser_password());
			stmt.setInt(2, user.getUser_role_id());
			stmt.setInt(3, user.getUser_id());
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.UserDao#createNewUser(java.lang.String,
	 * java.lang.String, java.lang.Integer)
	 */
	@Override
	public void createNewUser(String user_login, String user_password, Integer user_role_id) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"INSERT INTO sqllab.user (user_login, user_password, user_role_id)VALUES(?);\"");
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO sqllab.user (user_login, user_password, user_role_id)VALUES(?,?,?);");
			stmt.setString(1, user_login);
			stmt.setString(2, user_password);
			stmt.setInt(3, user_role_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.user was created");
			else
				logger.debug("New sqllab.user was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}

	}

	public User getUser() {
		return user;
	}

}
