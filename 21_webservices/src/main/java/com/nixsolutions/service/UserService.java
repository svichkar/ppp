package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.User;

public interface UserService {
	 List<User> getAllUsers();
	 User getUserById(String userId);
	 void createUser(String roleName, String usr, String pswd);
	 void updateUser(String userId, String roleName, String usr, String pswd);
	 void deleteUser(String userId);
	 User getUserByNameAndPswd(String name, String pswd);
}
