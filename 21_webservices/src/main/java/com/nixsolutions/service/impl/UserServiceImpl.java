package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dto.UserCustomerRole;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.User;
import com.nixsolutions.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	@Autowired
	private CustomerDAO customerDao;

	@Override
	public User getUserById(long id) {
		return userDao.findByPK(id);
	}

	@Override
	public User getByNameAndPassword(String username, String password) {
		return userDao.findByNameAndPassword(username, password);
	}

	@Override
	public User getByName(String username) {
		return userDao.findByName(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	@Override
	public void addUser(User user) {
		userDao.create(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);

	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public UserCustomerRole findByUser(User user) {
		UserCustomerRole userCustomerRole = null;
		if (user != null) {
			Customer customer = customerDao.findByUser(user);
			userCustomerRole = new UserCustomerRole();
			userCustomerRole.setCustomerId(customer.getCustomerId());
			userCustomerRole.setFname(customer.getFname());
			userCustomerRole.setLname(customer.getLname());
			userCustomerRole.setUsername(user.getUsername());
			userCustomerRole.setRole(user.getRole().getRoleName());
			userCustomerRole.setRoleId(user.getRole().getRoleId());
			userCustomerRole.setRole(user.getPassword());
		}
		return userCustomerRole;
	}

	@Override
	public List<UserCustomerRole> getAllUserCustomerRole() {
		List<UserCustomerRole> allUserCustomerRole = new ArrayList<>();
		List<User> users = new ArrayList<>();
		for (User user : users) {
			Customer customer = customerDao.findByUser(user);
			UserCustomerRole userCustomerRole = new UserCustomerRole();
			userCustomerRole.setCustomerId(customer.getCustomerId());
			userCustomerRole.setFname(customer.getFname());
			userCustomerRole.setLname(customer.getLname());
			userCustomerRole.setUsername(user.getUsername());
			userCustomerRole.setRole(user.getRole().getRoleName());
			userCustomerRole.setRoleId(user.getRole().getRoleId());
			userCustomerRole.setRole(user.getPassword());
			allUserCustomerRole.add(userCustomerRole);
		}
		return allUserCustomerRole;
	}

	@Override
	public UserCustomerRole findByPK(long userId) {
		UserCustomerRole userCustomerRole = null;
		User user = userDao.findByPK(userId);
		if (user != null) {
			Customer customer = customerDao.findByUser(user);
			userCustomerRole = new UserCustomerRole();
			userCustomerRole.setCustomerId(customer.getCustomerId());
			userCustomerRole.setFname(customer.getFname());
			userCustomerRole.setLname(customer.getLname());
			userCustomerRole.setUsername(user.getUsername());
			userCustomerRole.setRole(user.getRole().getRoleName());
			userCustomerRole.setRoleId(user.getRole().getRoleId());
			userCustomerRole.setRole(user.getPassword());
		}
		return userCustomerRole;
	}

}
