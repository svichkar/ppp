package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Subject;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public interface SubjectService {

    public void create(Subject subject);

    public void update(Subject subject);

    public void delete(Subject subject);

    public List<Subject> findAll();

    public Subject findById(Long id);

    public Subject findByName(String subjectName);

    public Subject findByNameAndTermId(String subjectName, Long termId);

    public List<Subject> findByTermId(Long termId);
}
