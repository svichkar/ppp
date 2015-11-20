package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.User;

public interface UserDao {
	public void create(String userName, String password, String email, int roleId);

	public void update(User user);

	public void delete(User user);

	public User getByUserId(int userId);
	
	public User getByUserName(String userName);

	public List<User> getAll();
}
