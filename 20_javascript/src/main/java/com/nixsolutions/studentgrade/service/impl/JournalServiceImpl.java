package com.nixsolutions.studentgrade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.studentgrade.dao.GradeDAO;
import com.nixsolutions.studentgrade.dao.JournalDAO;
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.service.JournalService;

@Service
public class JournalServiceImpl implements JournalService {

	@Autowired
	JournalDAO journalDao;
	
	@Autowired
	StudentDAO studentDao;
	
	@Autowired
	SubjectDAO subjectDao;

	@Autowired
	GradeDAO gradeDao;


	@Override
	public Long createJournal(Long studentId, Long subjectId, Long gradeId) {
		Journal newJournal = new Journal();
		newJournal.setStudent(studentDao.findStudentById(studentId));
		newJournal.setSubject(subjectDao.findSubjectById(subjectId));
		newJournal.setGrade(gradeDao.findGradeById(gradeId));
		journalDao.createJournal(newJournal);
		return newJournal.getJournalId();
	}

	@Override
	public void updateJournal(Long journalId, Long studentId, Long subjectId, Long gradeId) {
		Journal updatedJournal = journalDao.findJournalById(journalId);
		updatedJournal.setStudent(studentDao.findStudentById(studentId));
		updatedJournal.setSubject(subjectDao.findSubjectById(subjectId));
		updatedJournal.setGrade(gradeDao.findGradeById(gradeId));
		journalDao.updateJournal(updatedJournal);
	}

	@Override
	public void deleteJournal(Long journalId) {
		journalDao.deleteJournal(journalDao.findJournalById(journalId));
	}

	@Override
	public Journal findJournalById(Long journalId) {
		return journalDao.findJournalById(journalId);
	}

	@Override
	public List<Journal> findAllJournals() {
		return journalDao.findAllJournals();
	}

	@Override
	public List<Journal> findJournalsByStudentIdAndTermId(Long studentId, Long termId) {
		return journalDao.findJournalsByStudentIdAndTermId(studentId, termId);
	}

	@Override
	public Long findGPAByStudentIdAndTermId(Long studentId, Long termId) {
		return journalDao.findGPAByStudentIdAndTermId(studentId, termId);
	}

}
