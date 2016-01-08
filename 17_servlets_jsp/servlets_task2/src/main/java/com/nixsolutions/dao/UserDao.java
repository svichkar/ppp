package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.User;

public interface UserDao {
	public List<User> getAllUsers();
	public User getUserById(int userId);
	public void createUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	public User getUserByNameAndPswd(String name, String pswd);
	public boolean validateUserByNameAndPswd(String name, String pswd);
}
