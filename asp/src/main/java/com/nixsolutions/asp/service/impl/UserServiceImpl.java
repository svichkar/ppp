package com.nixsolutions.asp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.asp.dao.UserDao;
import com.nixsolutions.asp.entity.User;
import com.nixsolutions.asp.service.RoleService;
import com.nixsolutions.asp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public int create(User user) { 
		user.setRole(roleService.getByRoleName(user.getRole().getRoleName()));
		userDao.create(user);
		return userDao.getByUserName(user.getUserName()).getUserId();
	}

	@Override
	public void update(User user) {
		user.setRole(roleService.getByRoleName(user.getRole().getRoleName()));
		userDao.update(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public User getByUserId(int userId) {
		return userDao.getByUserId(userId);
	}

	@Override
	public User getByUserName(String userName) {
		return userDao.getByUserName(userName);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public boolean checkUser(String userName, String password) {
		User user = getByUserName(userName);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
}
