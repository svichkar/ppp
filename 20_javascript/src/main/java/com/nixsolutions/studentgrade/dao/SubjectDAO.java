package com.nixsolutions.studentgrade.dao;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Subject;

public interface SubjectDAO {

	public void createSubject(Subject subject);

	public void updateSubject(Subject subject);

	public void deleteSubject(Subject subject);

	public Subject findSubjectById(Long subjectId);

	public List<Subject> findAllSubjects();
	
	public List<Subject> findSubjectsByName(String subjectName);
	
	public List<Subject> findSubjectsByTermId(Long termId);
	
	public List<Subject> findSubjectByNameAndTermId(String subjectName, Long termId);

}
