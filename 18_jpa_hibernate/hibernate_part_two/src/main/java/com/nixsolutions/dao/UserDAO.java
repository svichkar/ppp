package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.hibernate.entity.User;

public interface UserDAO extends GenericDAO<User> {
	
	User getUserByLogin(String login);
	
	List<User> getUsers();
	
	List<User> getWorkers();
}
