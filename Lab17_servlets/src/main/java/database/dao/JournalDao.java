package database.dao;

import java.util.List;

import database.entities.Journal;

public interface JournalDao {

	public void create(int studentId, int subjectId, int rate);

	public void update(Journal journal);

	public void delete(Journal journal);

	public Journal getByJournalById(int journalId);

	public List<Journal> getAll();
	
	public List<Journal> getJournalByStudentId(int studentId);
	
	public List<Journal> getJournalBySubjectId(int subjectId);
}
