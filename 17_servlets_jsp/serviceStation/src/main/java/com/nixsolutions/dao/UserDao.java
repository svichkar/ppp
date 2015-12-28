/**
 * 
 */
package com.nixsolutions.dao;

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
	 * @return isValid @
	 */
	public boolean validate(String login, String password);

	/**
	 * getAllCustomers
	 * 
	 * @return @
	 */
	public List<User> getAllCustomers();

	/**
	 * getAllWorkers
	 * 
	 * @return @
	 */
	public List<User> getAllWorkers();

	/**
	 * getUserByID
	 * 
	 * @param userId
	 * @return @
	 */
	public User getUserByID(Integer userId);

	/**
	 * updateUser
	 * 
	 * @param user
	 * @
	 */
	public void updateUser(User user);

	/**
	 * @param user_login
	 * @param user_password
	 * @param user_role_id
	 * @
	 */
	public void createNewUser(String user_login, String user_password, Integer user_role_id);

	/**
	 * @return
	 */
	public User getUser();
}
