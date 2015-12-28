/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.UserRoleDao;
import com.nixsolutions.entity.UserRole;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author ������
 *
 */
public class UserRoleDaoImpl implements UserRoleDao {

	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "CREATE TABLE sqllab.user_role ( "
					+ "user_role_id INT IDENTITY, user_role_name VARCHAR(128) NOT NULL UNIQUE);";
			LOGGER.trace("Send query \"" + query + "\"");

			Statement stmt = connection.createStatement();
			stmt.execute(query);
			connection.commit();
			LOGGER.trace("Table sqllab.user_role was created");
			stmt.close();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DROP TABLE sqllab.user_role ;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				LOGGER.trace(" table sqllab.user_role was deleted");
			else
				LOGGER.debug("table sqllab.user_role was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public UserRole getUserRole(Integer user_role_id) {
		UserRole role = new UserRole();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.user_role WHERE user_role_id=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, user_role_id);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.user_role objects");
			while (set.next()) {
				role.setUserRoleId(set.getInt("user_role_id"));
				role.setUserRoleName(set.getString("user_role_name"));
			}
			stmt.close();
			return role;
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void createNewUserRole(String user_role_name) {
		try (Connection connection = ConnectionManager.getConnection()) {
			LOGGER.trace("Send query \"INSERT INTO sqllab.user_role (user_role_name)VALUES(?);\"");
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO sqllab.user_role (user_role_name)VALUES(?);");

			stmt.setString(1, user_role_name);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.user_role was created");
			else
				LOGGER.debug("New sqllab.cuser_role was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
