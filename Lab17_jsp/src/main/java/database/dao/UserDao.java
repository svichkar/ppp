package database.dao;

import java.util.List;

import database.entity.User;

public interface UserDao {
	public void create(String userName, String password, String email, int roleId);

	public void update(User user);

	public void delete(User user);

	public User getByUserId(int userId);
	
	public User getByUserName(String userName);

	public List<User> getAll();
	
	public boolean checkUser(String userName, String password);
}
