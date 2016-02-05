package com.nixsolutions.asp.service;

import com.nixsolutions.asp.entity.Journal;

import java.util.List;

public interface JournalService {

	void create(Journal journal);

	void update(Journal journal);

	void delete(Journal journal);

	Journal getByJournalById(int journalId);

	List<Journal> getAll();
	
	List<Journal> getJournalByStudentId(int studentId);
	
	List<Journal> getJournalBySubjectId(int subjectId);
}
