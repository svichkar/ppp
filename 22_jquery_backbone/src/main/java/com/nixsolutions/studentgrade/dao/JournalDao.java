package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.Journal;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface JournalDao {

    void create(Journal journal);

    void update(Journal journal);

    void delete(Journal journal);

    List<Journal> findAll();

    Journal findById(Long id);

    List<Journal> findByStudentAndTerm(Long studentId, Long termId);
}
