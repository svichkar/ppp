package com.nixsolutions.dao;

import com.nixsolutions.entity.Journal;
import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.Subject;

import java.util.List;

public interface JournalDao {

	public void create(Journal journal);

	public void update(Journal journal);

	public void delete(Journal journal);

	public Journal getByJournalById(int journalId);

	public List<Journal> getAll();
	
	public List<Journal> getJournalByStudent(Student student);
	
	public List<Journal> getJournalBySubject(Subject subject);
}
