package com.nixsolutions.dao;

import com.nixsolutions.entities.User;

public interface UserDAO extends GenericDao<User> {

	public User findByName(String username);

	public User findByNameAndPassword(String username, String password);

}
