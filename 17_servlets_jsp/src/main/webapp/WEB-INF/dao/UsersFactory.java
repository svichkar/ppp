package dao;

import java.util.List;

import dao.impl.UsersDao;

public class UsersFactory implements CommonFactory {
	private UsersDao usersDao;
	public UsersFactory() {
		usersDao = new UsersDao();
	}

	@Override
	public boolean addNewRow(List<String> columns, List<String> elements) {
		return usersDao.addNewRow(columns, elements);
	}

	@Override
	public boolean update(List<String> columns, List<String> elements,
			String whereState) {
		return usersDao.update(columns, elements, whereState);
	}

	@Override
	public boolean delete(String whereState) {
		return usersDao.delete(whereState);
	}

	@Override
	public List find(String[] searchField, String searchQuery) {
		return usersDao.find(searchField, searchQuery);
	}

}
