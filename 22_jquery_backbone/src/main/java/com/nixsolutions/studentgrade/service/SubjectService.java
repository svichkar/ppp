package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Subject;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public interface SubjectService {

    void create(Subject subject);

    void update(Subject subject);

    void delete(Subject subject);

    List<Subject> findAll();

    Subject findById(Long id);

    Subject findByName(String subjectName);

    Subject findByNameAndTermId(String subjectName, Long termId);

    List<Subject> findByTermId(Long termId);

    List<Subject> findByTermName(String term);

    List<Subject> findByNameAndTerm(String subject, String term);
}
