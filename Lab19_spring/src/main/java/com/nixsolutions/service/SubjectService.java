package com.nixsolutions.service;

import com.nixsolutions.entity.Subject;

import java.util.List;


public interface SubjectService {
	public void create(Subject subject);

	public void update(Subject subject);

	public void delete(Subject subject);

	public Subject getBySubjectId(int subjectId);

	public List<Subject> getAll();

	public List<Subject> getBySubjectName(String name);
	
	public List<Subject> getSubjectsByTermId(int termId);
}
