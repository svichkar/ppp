package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Journal;

import java.util.List;

/**
 * Created by konstantin on 1/29/2016.
 */
public interface JournalService {

    void create(Journal journal);

    void update(Journal journal);

    void delete(Journal journal);

    List<Journal> findAll();

    Journal findById(Long id);

    List<Journal> findByStudentAndTerm(Long studentId, Long termId);

    String getAverageScore(Long studentId, Long termId);
}
