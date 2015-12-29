package com.nixsolutions.dao;

import com.nixsolutions.entity.Journal;

import java.util.List;

public interface JournalDao {

	public void create(Journal journal);

	public void update(Journal journal);

	public void delete(Journal journal);

	public Journal getByJournalById(int journalId);

	public List<Journal> getAll();
	
	public List<Journal> getJournalByStudentId(int studentId);
	
	public List<Journal> getJournalBySubjectId(int subjectID);
}
