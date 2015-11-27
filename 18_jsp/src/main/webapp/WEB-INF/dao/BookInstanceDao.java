package dao;

import java.util.List;

public interface BookInstanceDao {
	public boolean addNewRow(List<String> columns, List<String> elements);
	public boolean update(List<String> columns, List<String> elements,
			String whereState);
	public boolean delete(String whereState);
	public List find(String[] searchField, String searchQuery);
	public boolean deleteByBookID(String bookId);
}
