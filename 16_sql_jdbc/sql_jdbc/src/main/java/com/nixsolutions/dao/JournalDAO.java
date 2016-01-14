package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Journal;

public interface JournalDAO {

	public void createJournal(Journal journal);

	public void updateJournal(Journal journal);

	public void deleteJournal(Journal journal);

	public Journal findJournalById(Long journalId);

	public List<Journal> findAllJournals();
}
