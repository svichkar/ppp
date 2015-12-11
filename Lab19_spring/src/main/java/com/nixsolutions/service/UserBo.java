package com.nixsolutions.service;

import com.nixsolutions.entity.User;

import java.util.List;

public interface UserBo {
	public void create(User user);

	public void update(User user);

	public void delete(User user);

	public User getByUserId(int userId);
	
	public User getByUserName(String userName);

	public List<User> getAll();

    public boolean checkUser(String userName, String password);
}
