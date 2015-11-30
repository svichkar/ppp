package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Journal;

public interface JournalDao {

    public Boolean create(Journal journal);

    public Boolean update(Journal journal);

    public Boolean delete(Journal journal);

    public Journal getJournalById(int journalId);

    public List<Journal> getAll();

    public List<Journal> getJournalByStudentId(int studentId);

    public List<Journal> getJournalBySubjectId(int subjectId);
    
    public List<Journal> getJournalByRate(String rate);
}
