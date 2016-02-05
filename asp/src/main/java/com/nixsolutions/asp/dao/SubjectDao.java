package com.nixsolutions.asp.dao;

import com.nixsolutions.asp.entity.Subject;

import java.util.List;


public interface SubjectDao {
	
	void create(Subject subject);

	void update(Subject subject);

	void delete(Subject subject);

	Subject getBySubjectId(int subjectId);

	List<Subject> getAll();

	List<Subject> getBySubjectName(String name);
	
	List<Subject> getSubjectsByTermId(int termId);
}
