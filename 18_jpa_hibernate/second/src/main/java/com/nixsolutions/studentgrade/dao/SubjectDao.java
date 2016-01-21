package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Subject;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface SubjectDao {

    public void create(Subject subject);

    public void update(Subject subject);

    public void delete(Subject subject);

    public List<Subject> findAll();

    public Subject findById(Long id);

    public Subject findByName(String subjectName);

    public Subject findByNameAndTermId(String subjectName, Long termId);

    public List<Subject> findByTermId(Long termId);
}
