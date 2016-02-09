package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.User;

public interface UserService {
	public List<User> getAllUsers();
	public User getUserById(Long userId);
	public void createUser(String roleName, String usr, String pswd);
	public void updateUser(String userId, String roleName, String usr, String pswd);
	public void deleteUser(User user);
	public User getUserByNameAndPswd(String name, String pswd);
}
