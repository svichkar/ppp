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

import com.nixsolutions.serviceStation.dAOFabrica.UserDao;
import com.nixsolutions.serviceStation.dbObjects.User;

/**
 * @author Михаил
 *
 */
public class UserDaoImpl implements UserDao {

	private final static Logger logger = LogManager.getLogger();
	private Connection dbConn;
	private User user;

 	public UserDaoImpl(Connection connection) {
		dbConn = connection;
		user = new User();}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"CREATE TABLE sqllab.users (  user_id INT IDENTITY, user_login VARCHAR(128) NOT NULL UNIQUE, user_password VARCHAR(17) NOT NULL);\"");

			dbConn.setAutoCommit(false);
			Statement stmt = dbConn.createStatement();
			stmt.execute(
					"CREATE TABLE sqllab.users ( user_id INT IDENTITY, user_login VARCHAR(128) NOT NULL UNIQUE, user_password VARCHAR(17) NOT NULL);");
			stmt.execute("ALTER TABLE sqllab.users " + "ADD COLUMN user_role_id INT NOT NULL;");
			stmt.execute("ALTER TABLE sqllab.users "
					+ "ADD FOREIGN KEY(user_role_id ) REFERENCES sqllab.user_role (user_role_id );");
			dbConn.commit();
			logger.trace("Table sqllab.users was created");
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

			PreparedStatement stmt = dbConn.prepareStatement("DROP TABLE sqllab.users ;");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace(" table sqllab.users was deleted");
			else
				logger.debug("table sqllab.users was not deleted");
			stmt.close();
		} catch (SQLException e) {
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
		try {
			logger.trace("Send query \"SELECT * FROM sqllab.user WHERE user_login=?\"");

			PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM sqllab.user WHERE user_login=?;");
			stmt.setString(1, login);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.user objects");
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
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	
	public User getUser() {
		return user;
	}

	
}
