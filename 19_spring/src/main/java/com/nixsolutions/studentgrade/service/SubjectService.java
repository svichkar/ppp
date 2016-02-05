package com.nixsolutions.studentgrade.service;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Subject;

public interface SubjectService {

	public Long createSubject(String subjectName, Long termId);

	public void updateSubject(Long subjectId, String subjectName, Long termId);

	public void deleteSubject(Long subjectId);

	public Subject findSubjectById(Long subjectId);

	public List<Subject> findAllSubjects();
	
	public List<Subject> findSubjectsByName(String subjectName);
	
	public List<Subject> findSubjectsByTermId(Long termId);
	
	public List<Subject> findSubjectByNameAndTermId(String subjectName, Long termId);

}
