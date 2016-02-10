package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.User;

public interface UserDao {
	 List<User> getAllUsers();
	 User getUserById(Long userId);
	 void createUser(User user);
	 void updateUser(User user);
	 void deleteUser(User user);
	 User getUserByNameAndPswd(String name, String pswd);
	 User getUserByName(String name);
}
