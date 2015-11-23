package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Journal;

public interface JournalDao {

    public void create(int studentId, int subjectId, String rate);

    public void update(Journal journal);

    public void delete(Journal journal);

    public Journal getJournalById(int journalId);

    public List<Journal> getAll();

    public List<Journal> getJournalByStudentId(int studentId);

    public List<Journal> getJournalBySubjectId(int subjectId);

    public List<Journal> getJournalByRate(String rate);
}
