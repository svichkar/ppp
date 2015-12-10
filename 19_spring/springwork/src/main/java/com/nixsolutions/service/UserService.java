package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.hibernate.entity.User;

public interface UserService {

	String getUserRole(String login);

	boolean isUserValid(String login, String password);

	User getUserByLogin(String login);

	List<User> getAllUsers();

	User getUserById(int id);

	List<User> getUsersWithRoleUser();

	List<User> getUsersWithRoleWorker();
}
