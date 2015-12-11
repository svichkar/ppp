package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.JournalDao;
import com.nixsolutions.entity.Journal;
import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.Subject;
import com.nixsolutions.service.JournalService;

@Service
public class JournalServiceImpl implements JournalService {
	
	@Autowired
	private JournalDao journalDao;
	
	@Override
	public void create(Journal journal) {
		journalDao.create(journal);
	}

	@Override
	public void update(Journal journal) {
		journalDao.update(journal);
	}

	@Override
	public void delete(Journal journal) {
		journalDao.delete(journal);
	}

	@Override
	public Journal getByJournalById(int journalId) {		
		return journalDao.getByJournalById(journalId);
	}

	@Override
	public List<Journal> getAll() {
		return journalDao.getAll();
	}

	@Override
	public List<Journal> getJournalByStudent(Student student) {
		return journalDao.getJournalByStudent(student);
	}

	@Override
	public List<Journal> getJournalBySubject(Subject subject) {
		return journalDao.getJournalBySubject(subject);
	}

}
