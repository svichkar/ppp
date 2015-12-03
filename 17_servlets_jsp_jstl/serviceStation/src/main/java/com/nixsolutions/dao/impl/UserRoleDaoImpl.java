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

	private final static Logger logger = LogManager.getLogger();

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
			String query = "CREATE TABLE sqllab.user_role ( user_role_id INT IDENTITY, user_role_name VARCHAR(128) NOT NULL UNIQUE);";
			logger.trace("Send query \"" + query + "\"");

			Statement stmt = connection.createStatement();
			stmt.execute(query);
			connection.commit();
			logger.trace("Table sqllab.user_role was created");
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
			String query = "DROP TABLE sqllab.user_role ;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table sqllab.user_role was deleted");
			else
				logger.debug("table sqllab.user_role was not deleted");
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
	 * com.nixsolutions.serviceStation.dAOFabrica.UserRoleDao#getUserRole(java.
	 * lang.Integer)
	 */
	@Override
	public UserRole getUserRole(Integer user_role_id) {
		UserRole role = new UserRole();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.user_role WHERE user_role_id=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, user_role_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.user_role objects");
			while (set.next()) {
				role.setUser_role_id(set.getInt("user_role_id"));
				role.setUser_role_name(set.getString("user_role_name"));
			}
			stmt.close();
			return role;
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
	 * create new sqllab.role for existing customer
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#createNewCar(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */

	@Override
	public void createNewUserRole(String user_role_name) {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			logger.trace("Send query \"INSERT INTO sqllab.user_role (user_role_name)VALUES(?);\"");
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO sqllab.user_role (user_role_name)VALUES(?);");

			stmt.setString(1, user_role_name);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.user_role was created");
			else
				logger.debug("New sqllab.cuser_role was not created");
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

}
