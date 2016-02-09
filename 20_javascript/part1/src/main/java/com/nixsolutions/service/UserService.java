package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.User;
import com.nixsolutions.entities.UserCustomerRole;

public interface UserService {

	public User getUserById(long id);

	public User getByNameAndPassword(String username, String password);
	
	public User getByName(String username);

	public List<User> getAllUsers();

	public void addUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user);

	public UserCustomerRole findByUser(User user);

	public List<UserCustomerRole> getAllUserCustomerRole();

	public UserCustomerRole findByPK(long userId);
}
