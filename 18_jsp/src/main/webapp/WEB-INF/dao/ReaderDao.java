package dao;

import java.util.List;

import entity.Reader;

public interface ReaderDao {
	public boolean addNewRow(List<String> columns, List<String> elements);
	public boolean update(List<String> columns, List<String> elements,
			String whereState);
	public boolean delete(String whereState);
	public List find(String[] searchField, String searchQuery);
	public boolean addReader(Reader reader);
	public List<Reader> getAllReaders();
	public boolean updateReader(Reader reader);
	public int getCountOfDebtBooksForParticularUser(String id);
}
