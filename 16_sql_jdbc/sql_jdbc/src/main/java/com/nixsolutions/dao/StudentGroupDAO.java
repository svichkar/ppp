package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.StudentGroup;

public interface StudentGroupDAO {

	public void createStudentGroup(StudentGroup group);

	public void updateStudentGroup(StudentGroup group);

	public void deleteStudentGroup(StudentGroup group);

	public StudentGroup findStudentGroupById(Long groupId);

	public List<StudentGroup> findAllStudentGroups();

}
