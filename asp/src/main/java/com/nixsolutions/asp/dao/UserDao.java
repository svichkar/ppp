package com.nixsolutions.asp.dao;

import com.nixsolutions.asp.entity.User;

import java.util.List;

public interface UserDao {
	
	void create(User user);

	void update(User user);

	void delete(User user);

	User getByUserId(int userId);
	
	User getByUserName(String userName);

	List<User> getAll();
}
