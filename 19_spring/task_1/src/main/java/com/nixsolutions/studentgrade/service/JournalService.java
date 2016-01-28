package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Journal;

import java.util.List;

/**
 * Created by konstantin on 1/29/2016.
 */
public interface JournalService {

    public void create(Journal journal);

    public void update(Journal journal);

    public void delete(Journal journal);

    public List<Journal> findAll();

    public Journal findById(Long id);

    public List<Journal> findByStudentAndTerm(Long studentId, Long termId);
}
