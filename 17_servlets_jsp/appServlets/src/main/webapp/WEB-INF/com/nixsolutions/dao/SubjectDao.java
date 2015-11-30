package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Subject;

public interface SubjectDao {

    public Boolean create(Subject subject);

    public Boolean update(Subject subject);

    public Boolean delete(Subject subject);

    public Subject getSubjectById(int subjectId);

    public Subject getSubjectByName(String subjectName);

    public List<Subject> getAll();

    public List<Subject> getSubjectsByTerm(int termId);
}
