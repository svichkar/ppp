package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.UserRole;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleService userRoleService;

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
	public User getUserByID(Integer userId) {
		return userDao.getUserByID(userId);
	}

	@Override
	public User getUserByID(String userId) {
		return getUserByID(Integer.decode(userId));
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void createNewUser(String userLogin, String userPassword, UserRole userRole) {
		userDao.createNewUser(userLogin, userPassword, userRole);
	}

	@Override
	public User getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}

	@Override
	public boolean deleteUserByID(Integer userId) {
		return userDao.deleteUserByID(userId);
	}

	@Override
	public boolean deleteUser(User user) {
		userDao.deleteUser(user);
		if (userDao.getUserByLogin(user.getUserLogin()) != null)
			return false;
		else
			return true;
	}

	@Override
	public void updateUser(String userId, String userLogin, String userPassword) {
		User user = getUserByID(userId);
		user.setUserLogin(userLogin);
		user.setUserPassword(userPassword);
		updateUser(user);
	}

	@Override
	public void createNewUser(String userLogin, String userPassword, String specializationId) {
		/*
		 * if (specializationId.equalsIgnoreCase("7")) createNewUser(userLogin,
		 * userPassword, userRoleService.getUserRole("ROLE_STOREKEEPER")); else
		 * if (specializationId.equalsIgnoreCase("6")) createNewUser(userLogin,
		 * userPassword, userRoleService.getUserRole("ROLE_MANAGER")); else
		 */
		createNewUser(userLogin, userPassword, userRoleService
				.getUserRole(Role.getRoleBySpecializationId(Integer.decode(specializationId)).toString()));
	}

	public enum Role {
		ROLE_MANAGER(6), ROLE_STOREKEEPER(7), ROLE_WORKER(3), ROLE_CUSTOMER(0);

		private int specializationId;

		private void setIntValue(int specializationId) {
			this.specializationId = specializationId;
		}

		public int getIntValue() {
			return specializationId;
		}

		private Role(int specializationId) {
			setIntValue(specializationId);
		}

		public static Role getRoleBySpecializationId(int specializationId) {
			switch (specializationId) {
			case 0:
				return Role.ROLE_CUSTOMER;
			case 6:
				return Role.ROLE_MANAGER;
			case 7:
				return Role.ROLE_STOREKEEPER;
			default:
				return Role.ROLE_WORKER;
			}
		}

	}
}
