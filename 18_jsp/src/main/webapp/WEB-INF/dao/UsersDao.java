package dao;

import java.util.List;

import entity.Users;

public interface UsersDao {
	public boolean addNewRow(List<String> columns, List<String> elements);
	public boolean update(List<String> columns, List<String> elements,
			String whereState);
	public boolean delete(String whereState);
	public List find(String[] searchField, String searchQuery);
	public List<Users> getAllUsers();
	public boolean addNewUser(Users user);
	public List<Users> getUserInfoByLogin(String login);
	public boolean updateUser(Users user);
	public boolean deleteUserById(String id);
}
