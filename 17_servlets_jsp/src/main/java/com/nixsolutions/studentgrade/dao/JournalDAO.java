package com.nixsolutions.studentgrade.dao;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Journal;

public interface JournalDAO {

	public Journal createJournal(int journalId, int studentId, int subjectId, int gradeId);

	public void updateJournal(Journal journal);

	public void deleteJournal(Journal journal);

	public Journal findJournalById(int journalId);

	public List<Journal> findAllJournals();
}
