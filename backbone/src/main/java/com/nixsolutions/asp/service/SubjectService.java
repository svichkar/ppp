package com.nixsolutions.asp.service;

import com.nixsolutions.asp.entity.Subject;

import java.util.List;


public interface SubjectService {
	void create(Subject subject);

	void update(Subject subject);

	void delete(Subject subject);

	Subject getBySubjectId(int subjectId);

	List<Subject> getAll();

	List<Subject> getBySubjectName(String name);
	
	List<Subject> getSubjectsByTermId(int termId);

	List<Subject> getSubjectsByQuery(String queryType, String query);
}
