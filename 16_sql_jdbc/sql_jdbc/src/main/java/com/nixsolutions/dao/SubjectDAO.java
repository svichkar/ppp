package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Subject;

public interface SubjectDAO {

	public void createSubject(Subject subject);

	public void updateSubject(Subject subject);

	public void deleteSubject(Subject subject);

	public Subject findSubjectById(Long subjectId);

	public List<Subject> findAllSubjects();

}
