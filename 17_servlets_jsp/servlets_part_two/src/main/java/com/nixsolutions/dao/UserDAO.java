package com.nixsolutions.dao;

import com.nixsolutions.entity.User;

public interface UserDAO extends GenericDAO<User> {
	
	public User getUserByLogin(String login);
}
