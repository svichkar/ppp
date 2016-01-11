package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Grade;

public interface GradeDAO {
	
	public void createGrade(Grade grade);

	public void updateGrade(Grade grade);

	public void deleteGrade(Grade grade);

	public Grade findGradeById(int gradeId);
	
	public List<Grade> findAllGrades();

}
