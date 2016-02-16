package com.nixsolutions.asp.dao;

import java.util.List;

import com.nixsolutions.asp.entity.Journal;

public interface JournalDao {

	void create(Journal journal);

	void update(Journal journal);

	void delete(Journal journal);

	Journal getByJournalById(int journalId);

	List<Journal> getAll();
	
	List<Journal> getJournalByStudentId(int studentId);
	
	List<Journal> getJournalBySubjectId(int subjectID);
}
