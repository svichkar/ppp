package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.dto.UserCustomerRole;
import com.nixsolutions.entities.User;

public interface UserService {

	User getUserById(long id);

	User getByNameAndPassword(String username, String password);

	User getByName(String username);

	List<User> getAllUsers();

	void addUser(User user);

	void deleteUser(User user);

	void updateUser(User user);

	UserCustomerRole findByUser(User user);

	List<UserCustomerRole> getAllUserCustomerRole();

	UserCustomerRole findByPK(long userId);
}
