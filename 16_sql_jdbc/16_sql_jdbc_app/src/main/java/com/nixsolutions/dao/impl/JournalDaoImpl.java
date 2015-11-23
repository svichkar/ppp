package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.JournalDao;
import com.nixsolutions.entity.Journal;

public class JournalDaoImpl extends AbstractDaoImpl implements JournalDao {
    private static String TABLE_NAME = "journal";

    @Override
    public void create(int studentId, int subjectId, String rate) {
	super.insert(new String[] { "student_id", "subject_id", "rate" },
		new String[] { String.valueOf(studentId), String.valueOf(subjectId), String.valueOf(rate) },
		TABLE_NAME);
    }

    @Override
    public void update(Journal journal) {
	super.update(journal, Journal.getMap(), TABLE_NAME);
    }

    @Override
    public void delete(Journal journal) {
	super.delete("journal_id", journal.getJournalId(), TABLE_NAME);
    }

    @Override
    public Journal getJournalById(int journalId) {
	List<Journal> journalList = super.find("journal_id", String.valueOf(journalId), Journal.getMap(), Journal.class);
	if (journalList.size() > 0) {
	    return journalList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Journal> getAll() {
	return super.find(null, null, Journal.getMap(), Journal.class);
    }

    @Override
    public List<Journal> getJournalByStudentId(int studentId) {
	return super.find("student_id", String.valueOf(studentId), Journal.getMap(), Journal.class);
    }

    @Override
    public List<Journal> getJournalBySubjectId(int subjectId) {
	return super.find("subject_id", String.valueOf(subjectId), Journal.getMap(), Journal.class);
    }

    @Override
    public List<Journal> getJournalByRate(String rate) {
	return super.find("rate", String.valueOf(rate), Journal.getMap(), Journal.class);
    }

}
