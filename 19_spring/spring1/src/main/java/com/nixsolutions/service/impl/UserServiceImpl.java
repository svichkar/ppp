package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserDao userDao;
	@Autowired
	RoleDao roleDao;
	
	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User getUserById(Long userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public void createUser(String roleName, String usr, String pswd) {
		Role role = roleDao.getRoleByName(roleName);
		User createUser = new User(usr, pswd, role);
		userDao.createUser(createUser);
	}

	@Override
	public void updateUser(String userId, String roleName, String usr, String pswd) {
		User updUser = userDao
				.getUserById(Long.parseLong(userId));
		Role role = roleDao.getRoleByName(roleName);
		updUser.setRole(role);
		updUser.setUserName(usr);
		updUser.setUserPassword(pswd);
		userDao.updateUser(updUser);
	}

	@Override
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Override
	public User getUserByNameAndPswd(String name, String pswd) {
		return userDao.getUserByNameAndPswd(name, pswd);
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		return userDao.getUserByName(name);
	}


}
