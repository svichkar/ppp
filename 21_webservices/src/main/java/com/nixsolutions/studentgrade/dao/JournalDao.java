package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.Journal;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface JournalDao {

    public void create(Journal journal);

    public void update(Journal journal);

    public void delete(Journal journal);

    public List<Journal> findAll();

    public Journal findById(Long id);

    public List<Journal> findByStudentAndTerm(Long studentId, Long termId);
}
