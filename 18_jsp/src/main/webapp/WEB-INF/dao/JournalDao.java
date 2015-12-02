package dao;

import java.util.List;

import entity.Journal;

public interface JournalDao {
	public boolean addNewRow(List<String> columns, List<String> elements);
	public boolean update(List<String> columns, List<String> elements,
			String whereState);
	public boolean delete(String whereState);
	public List find(String[] searchField, String searchQuery);
	public List<Journal> getAllJournalRecords();
	public List<Journal> addInfoToJournal(List<Journal> journalList);
	public boolean deleteJournalRecordById(String id);
	
}
