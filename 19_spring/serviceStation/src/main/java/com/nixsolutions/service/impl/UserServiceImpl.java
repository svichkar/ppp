package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.UserRole;
import com.nixsolutions.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean validate(String login, String password) {
		return userDao.validate(login, password);
	}

	@Override
	public List<User> getAllCustomers() {
		return userDao.getAllCustomers();
	}

	@Override
	public List<User> getAllWorkers() {
		return userDao.getAllWorkers();
	}

	@Override
	public User getUserByID(Integer user_id) {
		return userDao.getUserByID(user_id);
	}

	@Override
	public User getUserByID(String user_id) {
		return getUserByID(Integer.decode(user_id));
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void createNewUser(String user_login, String user_password, UserRole user_role) {
		userDao.createNewUser(user_login, user_password, user_role);
	}

	@Override
	public User getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}

	@Override
	public boolean deleteUserByID(Integer user_id) {
		return userDao.deleteUserByID(user_id);
	}

	@Override
	public boolean deleteUser(User user) {
		userDao.deleteUser(user);
		if (userDao.getUserByLogin(user.getUser_login()) != null)
			return false;
		else
			return true;
	}
}
