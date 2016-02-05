package com.nixsolutions.studentgrade.service;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Grade;

public interface GradeService {
	
	public void createGrade(Grade grade);

	public void updateGrade(Grade grade);

	public void deleteGrade(Grade grade);

	public Grade findGradeById(Long gradeId);
	
	public List<Grade> findAllGrades();

}
