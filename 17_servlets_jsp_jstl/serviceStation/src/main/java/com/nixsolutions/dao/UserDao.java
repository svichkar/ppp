/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.User;

/**
 * @author ������
 *
 */
public interface UserDao extends DBTables {
	/**
	 * validate user
	 * 
	 * @param login
	 * @param password
	 * @return isValid
	 * @throws SQLException
	 */
	public boolean validate(String login, String password);

	/**
	 * getAllCustomers
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<User> getAllCustomers();

	/**
	 * getAllWorkers
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<User> getAllWorkers();

	/**
	 * getUserByID
	 * 
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public User getUserByID(Integer user_id);

	/**
	 * updateUser
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void updateUser(User user);

	/**
	 * @param user_login
	 * @param user_password
	 * @param user_role_id
	 * @throws SQLException
	 */
	public void createNewUser(String user_login, String user_password, Integer user_role_id);

	public User getUserByLogin(String login);

	public void deleteUserByID(Integer user_id);
}
