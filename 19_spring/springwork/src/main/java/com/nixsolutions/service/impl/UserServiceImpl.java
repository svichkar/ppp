package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	public String getUserRole(String login) {
		User user = userDao.getUserByLogin(login);
		if (user != null) {
			return user.getRole().getRoleName();
		} else {
			return "User does not exist.";
		}
	}

	public boolean isUserValid(String login, String password) {
		User user = userDao.getUserByLogin(login);
		return user != null && (user.getUserPassword().equals(password));
	}

	public User getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}

	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	public User getUserById(int id) {
		return userDao.getByPK(id);
	}

	public List<User> getUsersWithRoleUser() {
		return userDao.getUsers();
	}

	public List<User> getUsersWithRoleWorker() {
		return userDao.getWorkers();
	}
}
