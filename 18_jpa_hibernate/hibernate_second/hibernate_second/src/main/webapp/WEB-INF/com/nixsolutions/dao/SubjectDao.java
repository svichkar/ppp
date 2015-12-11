package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Subject;
import com.nixsolutions.entity.Term;

public interface SubjectDao {

    public Boolean create(Subject subject);

    public Boolean update(Subject subject);

    public Boolean delete(Subject subject);

    public Subject getSubjectById(Integer subjectId);

    public Subject getSubjectByName(String subjectName);

    public List<Subject> getAll();

    public List<Subject> getSubjectsByTerm(Term term);
}
