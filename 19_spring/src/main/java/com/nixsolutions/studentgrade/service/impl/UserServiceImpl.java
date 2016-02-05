package com.nixsolutions.studentgrade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.studentgrade.dao.RoleDAO;
import com.nixsolutions.studentgrade.dao.UserDAO;
import com.nixsolutions.studentgrade.entity.User;
import com.nixsolutions.studentgrade.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;
	
	@Autowired
	RoleDAO roleDao;

	@Override
	public Long createUser(String login, String password, String email, Long roleId) {
		User newUser = new User();
		newUser.setLogin(login);
		newUser.setPassword(password);		
		newUser.setEmail(email);
		newUser.setRole(roleDao.findRoleById(roleId));							
		userDao.createUser(newUser);
		return newUser.getUserId();
	}

	@Override
	public void updateUser(Long userId, String login, String password, String email, Long roleId) {
		User updatedUser = userDao.findUserById(userId);
		updatedUser.setLogin(login);
		updatedUser.setPassword(password);
		updatedUser.setEmail(email);
		updatedUser.setRole(roleDao.findRoleById(roleId));
		userDao.updateUser(updatedUser);
	}

	@Override
	public void deleteUser(Long userId) {
		userDao.deleteUser(userDao.findUserById(userId));
	}

	@Override
	public User findUserById(Long userId) {
		return userDao.findUserById(userId);
	}

	@Override
	public User findUserByLogin(String login) {
		return userDao.findUserByLogin(login);
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

}
