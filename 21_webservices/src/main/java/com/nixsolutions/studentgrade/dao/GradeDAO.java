package com.nixsolutions.studentgrade.dao;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Grade;

public interface GradeDAO {
	
	public void createGrade(Grade grade);

	public void updateGrade(Grade grade);

	public void deleteGrade(Grade grade);

	public Grade findGradeById(Long gradeId);
	
	public List<Grade> findAllGrades();

}
