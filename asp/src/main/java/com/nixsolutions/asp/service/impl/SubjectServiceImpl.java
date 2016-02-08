package com.nixsolutions.asp.service.impl;

import java.util.List;

import com.nixsolutions.asp.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.asp.dao.SubjectDao;
import com.nixsolutions.asp.entity.Subject;
import com.nixsolutions.asp.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;
	private SubjectService subjectService;
	private TermService termService;

	@Override
	public void create(Subject subject) {
		subjectDao.create(subject);
	}

	@Override
	public void update(Subject subject) {
		subjectDao.update(subject);
	}

	@Override
	public void delete(Subject subject) {
		subjectDao.delete(subject);
	}

	@Override
	public Subject getBySubjectId(int subjectId) {
		return subjectDao.getBySubjectId(subjectId);
	}

	@Override
	public List<Subject> getAll() {
		return subjectDao.getAll();
	}

	@Override
	public List<Subject> getBySubjectName(String name) {
		return subjectDao.getBySubjectName(name);
	}

	@Override
	public List<Subject> getSubjectsByTermId(int termId) {
		return subjectDao.getSubjectsByTermId(termId);
	}

	@Override
	public List<Subject> getSubjectsByQuery(String queryType, String query) {
		if (queryType.equals("subject")) {
			return subjectService.getBySubjectName(query);
		} else {
			return subjectService.getSubjectsByTermId(termService.getByTermAlias(query).getTermId());
		}
	}

}
