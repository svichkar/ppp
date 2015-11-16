/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.UserRoleDao;
import com.nixsolutions.serviceStation.dbObjects.UserRole;

/**
 * @author Михаил
 *
 */
public class UserRoleDaoImpl implements UserRoleDao {

	private final static Logger logger = LogManager.getLogger();
	private Connection dbConn;

	public UserRoleDaoImpl(Connection connection) {
		dbConn = connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		try {
			logger.trace(
					"Send query \"CREATE TABLE sqllab.user_role ( user_role_id INT IDENTITY, user_role_name VARCHAR(128) NOT NULL UNIQUE);\"");

			dbConn.setAutoCommit(false);
			Statement stmt = dbConn.createStatement();
			stmt.execute(
					"CREATE TABLE sqllab.user_role ( user_role_id INT IDENTITY, user_role_name VARCHAR(128) NOT NULL UNIQUE);");
			dbConn.commit();
			logger.trace("Table sqllab.user_role was created");
			stmt.close();
			dbConn.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DROP TABLE sqllab.users ;\"");

			PreparedStatement stmt = dbConn.prepareStatement("DROP TABLE sqllab.user_role ;");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table sqllab.user_role was deleted");
			else
				logger.debug("table sqllab.user_role was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
		try {
			logger.trace("Send query \"SELECT * FROM sqllab.user_role WHERE user_role_id=?\"");

			PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM sqllab.user_role WHERE user_role_id=?;");
			stmt.setInt(1, user_role_id);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.user_role objects");
			while (set.next()) {
				role.setUser_role_id(set.getInt("user_role_id"));
				role.setUser_role_name(set.getString("user_role_name"));
			}
			stmt.close();
			return role;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
