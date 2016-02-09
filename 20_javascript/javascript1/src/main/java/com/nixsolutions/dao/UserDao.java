package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.User;

public interface UserDao {
	public List<User> getAllUsers();
	public User getUserById(Long userId);
	public void createUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	public User getUserByNameAndPswd(String name, String pswd);
	public User getUserByName(String name);
}
