package com.nixsolutions.studentgrade.dao;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Journal;

public interface JournalDAO {

	public void createJournal(Journal journal);

	public void updateJournal(Journal journal);

	public void deleteJournal(Journal journal);

	public Journal findJournalById(Long journalId);

	public List<Journal> findAllJournals();
	
	public List<Journal> findJournalsByStudentIdAndTermId(Long studentId, Long termId);
	
	public Long findGPAByStudentIdAndTermId(Long studentId, Long termId);
}
