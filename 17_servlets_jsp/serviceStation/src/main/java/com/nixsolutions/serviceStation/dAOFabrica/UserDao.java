/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.User;

/**
 * @author Михаил
 *
 */
public interface UserDao extends DBTables {
	/**
	 * validate user
	 * 
	 * @param login
	 * @param password
	 * @return isValid
	 */
	public boolean validate(String login, String password);

	/**
	 * getAllCustomers
	 * 
	 * @return
	 */
	public List<User> getAllCustomers();

	/**
	 * getAllWorkers
	 * 
	 * @return
	 */
	public List<User> getAllWorkers();

	/**
	 * getUserByID
	 * 
	 * @param user_id
	 * @return
	 */
	public User getUserByID(Integer user_id);

	/**
	 * updateUser
	 * @param user
	 */
	public void updateUser(User user);
}
