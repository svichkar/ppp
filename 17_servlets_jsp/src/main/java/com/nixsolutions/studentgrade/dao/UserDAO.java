package com.nixsolutions.studentgrade.dao;

import java.util.List;

import com.nixsolutions.studentgrade.entity.User;

public interface UserDAO {
	
	public User createUser(int userId, String login, String password, int roleId);

	public void updateUser(User user);

	public void deleteUser(User user);

	public User findUserById(int userId);
	
	public User findUserByLogin(String login);
	
	public List<User> findAllUsers();

}
