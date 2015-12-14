package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.User;

public interface UserDAO extends GenericDAO<User> {
	
	public User getUserByLogin(String login);
	
	public List<User> getUsers();
	
	public List<User> getWorkers();
}
