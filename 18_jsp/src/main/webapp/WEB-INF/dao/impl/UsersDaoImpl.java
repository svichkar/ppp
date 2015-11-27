package dao.impl;

import java.util.Arrays;
import java.util.List;

import dao.UsersDao;
import entity.Users;

public class UsersDaoImpl extends AbstractDaoImpl implements UsersDao {

	@Override
	public List<Users> getAllUsers() {
		return super.find(null, null);
	}

	@Override
	public boolean addNewUser(Users users) {
		return super.addNewRow(Arrays.asList("login", "password", "role"),
				Arrays.asList(users.getLogin(), users.getPassword(), users.getRole()));
	}

	@Override
	public List<Users> getUserInfoByLogin(String login) {

		return super.find(new String[]{"password", "role"},
				"login='" + login + "';");
	}

	@Override
	public boolean updateUser(Users user) {
		
		return super.update(Arrays.asList("login", "password", "role"),
				Arrays.asList(user.getLogin(), user.getPassword(), user.getRole()), "id=" + user.getId());
	}

	@Override
	public boolean deleteUserById(String id) {
		
		return super.delete("id=" + id);
	}

}