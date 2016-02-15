package com.nixsolutions.studentgrade.service;

import java.util.List;

import com.nixsolutions.studentgrade.entity.User;

public interface UserService {
	
	public Long createUser(String login, String password, String email, Long roleId);

	public void updateUser(Long userId, String login, String password, String email, Long roleId);

	public void deleteUser(Long userId);

	public User findUserById(Long userId);
	
	public User findUserByLogin(String login);
	
	public List<User> findAllUsers();

}
