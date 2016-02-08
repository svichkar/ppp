package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.Subject;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface SubjectDao {

    void create(Subject subject);

    void update(Subject subject);

    void delete(Subject subject);

    List<Subject> findAll();

    Subject findById(Long id);

    Subject findByName(String subjectName);

    Subject findByNameAndTermId(String subjectName, Long termId);

    List<Subject> findByTermId(Long termId);

    List<Subject> findByTermName(String termName);

    Subject findByNameAndTerm(String subjectName, String termName);
}
