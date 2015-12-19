package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.JournalDao;
import com.nixsolutions.entity.Journal;
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
	public List<Journal> getJournalByStudentId(int studentId) {
		return journalDao.getJournalByStudentId(studentId);
	}

	@Override
	public List<Journal> getJournalBySubjectId(int subjectId) {
		return journalDao.getJournalBySubjectId(subjectId);
	}

}
