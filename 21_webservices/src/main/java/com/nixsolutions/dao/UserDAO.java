package com.nixsolutions.dao;

import com.nixsolutions.entities.User;

public interface UserDAO extends GenericDao<User> {

	User findByName(String username);

	User findByNameAndPassword(String username, String password);

}
