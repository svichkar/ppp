package com.nixsolutions.studentgrade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectDAO subjectDao;
	
	@Autowired
	TermDAO termDao;

	@Override
	public Long createSubject(String subjectName, Long termId) {
		Subject newSubject = new Subject();
		newSubject.setSubjectName(subjectName);
		newSubject.setTerm(termDao.findTermById(termId));
		subjectDao.createSubject(newSubject);
		return newSubject.getSubjectId();		
	}

	@Override
	public void updateSubject(Long subjectId, String subjectName, Long termId) {
		Subject updatedSubject = subjectDao.findSubjectById(subjectId);
		updatedSubject.setSubjectName(subjectName);
		updatedSubject.setTerm(termDao.findTermById(termId));
		subjectDao.updateSubject(updatedSubject);
	}

	@Override
	public void deleteSubject(Long subjectId) {
		subjectDao.deleteSubject(subjectDao.findSubjectById(subjectId));
	}

	@Override
	public Subject findSubjectById(Long subjectId) {
		return subjectDao.findSubjectById(subjectId);
	}

	@Override
	public List<Subject> findAllSubjects() {
		return subjectDao.findAllSubjects();
	}

	@Override
	public List<Subject> findSubjectsByName(String subjectName) {
		return subjectDao.findSubjectsByName(subjectName);
	}

	@Override
	public List<Subject> findSubjectsByTermId(Long termId) {
		return subjectDao.findSubjectsByTermId(termId);
	}

	@Override
	public List<Subject> findSubjectByNameAndTermId(String subjectName, Long termId) {
		return subjectDao.findSubjectByNameAndTermId(subjectName, termId);
	}

}
