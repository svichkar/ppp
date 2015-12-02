package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.entity.Subject;

public class SubjectDaoImpl extends AbstractDaoImpl implements SubjectDao {
    private static String TABLE_NAME = "subject";

    @Override
    public Boolean create(Subject subject) {
	return super.insert(subject, Subject.getMap(), TABLE_NAME);
    }

    @Override
    public Boolean update(Subject subject) {
	return super.update(subject, Subject.getMap(), TABLE_NAME);
    }

    @Override
    public Boolean delete(Subject subject) {
	return super.delete("subject_id", subject.getSubjectId(), TABLE_NAME);
    }

    @Override
    public Subject getSubjectById(int subjectId) {
	List<Subject> subjectList = super.find("subject_id", String.valueOf(subjectId), Subject.getMap(), Subject.class);
	if (subjectList.size() > 0) {
	    return subjectList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public Subject getSubjectByName(String subjectName) {
	List<Subject> subjectList = super.find("subject_name", subjectName, Subject.getMap(), Subject.class);
	if (subjectList.size() > 0) {
	    return subjectList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Subject> getAll() {
	return super.find(null, null, Subject.getMap(), Subject.class);
    }

    @Override
    public List<Subject> getSubjectsByTerm(int termId) {
	return super.find("term_id", String.valueOf(termId), Subject.getMap(), Subject.class);
    }

}
