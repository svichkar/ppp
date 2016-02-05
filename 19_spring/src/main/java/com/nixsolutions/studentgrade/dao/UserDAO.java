package com.nixsolutions.studentgrade.dao;

import java.util.List;

import com.nixsolutions.studentgrade.entity.User;

public interface UserDAO {
	
	public void createUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public User findUserById(Long userId);
	
	public User findUserByLogin(String login);
	
	public List<User> findAllUsers();

}
