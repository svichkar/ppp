package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.entity.Subject;
import com.nixsolutions.entity.Term;

public class SubjectDaoImpl extends AbstractDaoImpl implements SubjectDao {

    @Override
    public Boolean create(Subject subject) {
	return super.insert(subject);
    }

    @Override
    public Boolean update(Subject subject) {
	return super.update(subject);
    }

    @Override
    public Boolean delete(Subject subject) {
	return super.delete(subject);
    }

    @Override
    public Subject getSubjectById(Integer subjectId) {
	Subject s = new Subject();
	s.setSubjectId(subjectId);
	List<Subject> subjectList = super.findBySeveralFields(s, new String[]{"subjectId"});
	if (subjectList.size() > 0) {
	    return subjectList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public Subject getSubjectByName(String subjectName) {
	Subject s = new Subject();
	s.setSubjectName(subjectName);
	List<Subject> subjectList = super.findBySeveralFields(s, new String[]{"subjectName"});
	if (subjectList.size() > 0) {
	    return subjectList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Subject> getAll() {
	return super.findBySeveralFields(new Subject(), null);
    }

    @Override
    public List<Subject> getSubjectsByTerm(Term term) {
	Subject s = new Subject();
	s.setTerm(term);
	return super.findBySeveralFields(s, new String[]{"term"});
    }

}
