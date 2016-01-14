package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.User;
import com.nixsolutions.entity.UserRole;

public interface UserService {
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
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	User getUserByID(Integer userId);

	/**
	 * getUserByID
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	User getUserByID(String userId);

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
	void createNewUser(String userLogin, String userPassword, UserRole userRole);

	/**
	 * @param login
	 * @return
	 */
	User getUserByLogin(String login);

	/**
	 * @param userId
	 * @return
	 */
	boolean deleteUserByID(Integer userId);

	/**
	 * @param user
	 * @return
	 */
	boolean deleteUser(User user);

	void updateUser(String userId, String userLogin, String userPassword);

	void createNewUser(String userLogin, String userPassword, String specializationId);

}
