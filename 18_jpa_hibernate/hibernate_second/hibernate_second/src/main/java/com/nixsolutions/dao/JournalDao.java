package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Journal;
import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.Subject;

public interface JournalDao {

    public Boolean create(Journal journal);

    public Boolean update(Journal journal);

    public Boolean delete(Journal journal);

    public Journal getJournalById(Integer journalId);

    public List<Journal> getAll();

    public List<Journal> getJournalByStudent(Student student);

    public List<Journal> getJournalBySubject(Subject subject);
    
    public List<Journal> getJournalByRate(String rate);
}
