package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.JournalDao;
import com.nixsolutions.entity.Journal;
import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.Subject;

public class JournalDaoImpl extends AbstractDaoImpl implements JournalDao {
    
    public JournalDaoImpl() {
    }
    
    @Override
    public Boolean create(Journal journal) {
	return super.insert(journal);
    }

    @Override
    public Boolean update(Journal journal) {
	return super.update(journal);
    }

    @Override
    public Boolean delete(Journal journal) {
	return super.delete(journal);
    }

    @Override
    public Journal getJournalById(Integer journalId) {
	Journal j = new Journal();
	j.setJournalId(journalId);
	List<Journal> journalList = super.findBySeveralFields(j, new String[]{"journalId"});
	if (journalList.size() > 0) {
	    return journalList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Journal> getAll() {
	return super.findBySeveralFields(new Journal(), null);
    }

    @Override
    public List<Journal> getJournalByStudent(Student student) {
	Journal j = new Journal();
	j.setStudent(student);
	return super.findBySeveralFields(j, new String[]{"student"});
    }

    @Override
    public List<Journal> getJournalBySubject(Subject subject) {
	Journal j = new Journal();
	j.setSubject(subject);
	return super.findBySeveralFields(j, new String[]{"subject"});
    }

    @Override
    public List<Journal> getJournalByRate(String rate) {
	Journal j = new Journal();
	return super.findBySeveralFields(j, new String[]{"rate"});
    }

}
