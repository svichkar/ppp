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

	private final static Logger LOGGER = LogManager.getLogger();
	private User user;

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DELETE FROM sqllab.worker_status WHERE worker_status_name=?";
			LOGGER.trace("Send query \"" + query + "\"");

			Statement stmt = connection.createStatement();
			stmt.execute(
					"CREATE TABLE sqllab.users ( userId INT IDENTITY, user_login VARCHAR(128) NOT NULL UNIQUE, user_password VARCHAR(17) NOT NULL);");
			stmt.execute("ALTER TABLE sqllab.users " + "ADD COLUMN user_role_id INT NOT NULL;");
			stmt.execute("ALTER TABLE sqllab.users "
					+ "ADD FOREIGN KEY(user_role_id ) REFERENCES sqllab.user_role (user_role_id );");
			connection.commit();
			LOGGER.trace("Table sqllab.users was created");
			stmt.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DROP TABLE sqllab.users ;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				LOGGER.trace(" table sqllab.users was deleted");
			else
				LOGGER.debug("table sqllab.users was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public boolean validate(String login, String password) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.user WHERE user_login=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, login);
			ResultSet set = stmt.executeQuery();

			LOGGER.trace("Generate the user object for " + login);
			user = new User();
			while (set.next()) {
				user.setUserId(set.getInt("user_id"));
				user.setUserLogin(set.getString("user_login"));
				user.setUserPassword(set.getString("user_password"));
				user.setUserRoleId(set.getInt("user_role_id"));
			}
			stmt.close();
			if (password.equals(user.getUserPassword())) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public List<User> getAllCustomers() {
		List<User> users = new ArrayList<User>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.user WHERE  user_role_id=2;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.user objects");
			while (set.next()) {
				User qwerty = new User();
				qwerty.setUserId(set.getInt("user_id"));
				qwerty.setUserLogin(set.getString("user_login"));
				qwerty.setUserPassword(set.getString("user_password"));
				qwerty.setUserRoleId(set.getInt("user_role_id"));
				users.add(qwerty);
			}
			stmt.close();
			return users;
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<User> getAllWorkers() {
		List<User> users = new ArrayList<User>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.user WHERE  user_role_id!=2;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.user objects");
			while (set.next()) {
				User qwerty = new User();
				qwerty.setUserId(set.getInt("user_id"));
				qwerty.setUserLogin(set.getString("user_login"));
				qwerty.setUserPassword(set.getString("user_password"));
				qwerty.setUserRoleId(set.getInt("user_role_id"));
				users.add(qwerty);
			}
			stmt.close();
			return users;
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public User getUserByID(Integer userId) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.user WHERE userId=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.user objects");
			while (set.next()) {
				User qwerty = new User();
				qwerty.setUserId(set.getInt("user_id"));
				qwerty.setUserLogin(set.getString("user_login"));
				qwerty.setUserPassword(set.getString("user_password"));
				qwerty.setUserRoleId(set.getInt("user_role_id"));
				user = qwerty;
				return qwerty;
			}
			stmt.close();

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "UPDATE sqllab.user SET user_password =?, user_role_id =? WHERE userId =?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, user.getUserPassword());
			stmt.setInt(2, user.getUserRoleId());
			stmt.setInt(3, user.getUserId());
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void createNewUser(String user_login, String user_password, Integer user_role_id) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"INSERT INTO sqllab.user (user_login, user_password, user_role_id)VALUES(?);\"");
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO sqllab.user (user_login, user_password, user_role_id)VALUES(?,?,?);");
			stmt.setString(1, user_login);
			stmt.setString(2, user_password);
			stmt.setInt(3, user_role_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.user was created");
			else
				LOGGER.debug("New sqllab.user was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public User getUser() {
		return user;
	}

}
