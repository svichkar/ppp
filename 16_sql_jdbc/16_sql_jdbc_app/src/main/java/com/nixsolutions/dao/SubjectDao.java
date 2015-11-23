package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Subject;

public interface SubjectDao {

    public void create(String subjectName, int termId);

    public void update(Subject subject);

    public void delete(Subject subject);

    public Subject getSubjectById(int subjectId);

    public Subject getSubjectByName(String subjectName);

    public List<Subject> getAll();

    public List<Subject> getSubjectsByTerm(int termId);
}
