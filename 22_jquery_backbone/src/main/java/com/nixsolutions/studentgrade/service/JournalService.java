package com.nixsolutions.studentgrade.service;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Journal;

public interface JournalService {

	public Long createJournal(Long studentId, Long subjectId, Long gradeId);

	public void updateJournal(Long journalId, Long studentId, Long subjectId, Long gradeId);

	public void deleteJournal(Long journalId);

	public Journal findJournalById(Long journalId);

	public List<Journal> findAllJournals();
	
	public List<Journal> findJournalsByStudentIdAndTermId(Long studentId, Long termId);
	
	public Long findGPAByStudentIdAndTermId(Long studentId, Long termId);
}
