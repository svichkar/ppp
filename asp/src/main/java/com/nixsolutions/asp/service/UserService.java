package com.nixsolutions.asp.service;

import com.nixsolutions.asp.entity.User;

import java.util.List;

public interface UserService {
	int create(User user);

	void update(User user);

	void delete(int userId);

	User getByUserId(int userId);
	
	User getByUserName(String userName);

	List<User> getAll();
	
	boolean checkUser(String userName, String password);
}
