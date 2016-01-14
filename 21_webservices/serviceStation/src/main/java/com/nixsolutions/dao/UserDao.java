/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.User;
import com.nixsolutions.entity.UserRole;

public interface UserDao {
	/**
	 * validate user
	 * 
	 * @param login
	 * @param password
	 * @return isValid
	 * @throws SQLException
	 */
	boolean validate(String login, String password);

	/**
	 * getAllCustomers
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<User> getAllCustomers();

	/**
	 * getAllWorkers
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<User> getAllWorkers();

	/**
	 * getUserByID
	 * 
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	User getUserByID(Integer user_id);

	/**
	 * updateUser
	 * 
	 * @param user
	 * @throws SQLException
	 */
	void updateUser(User user);

	/**
	 * @param user_login
	 * @param user_password
	 * @param user_role_id
	 * @throws SQLException
	 */
	void createNewUser(String user_login, String user_password, UserRole user_role);

	/**
	 * @param login
	 * @return
	 */
	User getUserByLogin(String login);

	/**
	 * @param user_id
	 * @return
	 */
	boolean deleteUserByID(Integer user_id);

	/**
	 * deleteUser
	 * 
	 * @param user
	 * @throws SQLException
	 */
	void deleteUser(User user);
}
