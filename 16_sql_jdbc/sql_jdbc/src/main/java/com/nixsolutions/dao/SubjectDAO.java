package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Subject;

public interface SubjectDAO {

	public Subject createSubject(int subjectId, String subjectName, int termId);

	public void updateSubject(Subject subject);

	public void deleteSubject(Subject subject);

	public Subject findSubjectById(int subjectId);

	public List<Subject> findAllSubjects();

}
