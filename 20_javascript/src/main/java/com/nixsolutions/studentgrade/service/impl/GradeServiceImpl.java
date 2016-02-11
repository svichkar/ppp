package com.nixsolutions.studentgrade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.studentgrade.dao.GradeDAO;
import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	GradeDAO gradeDao;

	@Override
	public void createGrade(Grade grade) {
		gradeDao.createGrade(grade);
	}

	@Override
	public void updateGrade(Grade grade) {
		gradeDao.updateGrade(grade);
	}

	@Override
	public void deleteGrade(Grade grade) {
		gradeDao.deleteGrade(grade);
	}

	@Override
	public Grade findGradeById(Long gradeId) {
		return gradeDao.findGradeById(gradeId);
	}

	@Override
	public List<Grade> findAllGrades() {
		return gradeDao.findAllGrades();
	}

}
