package com.nixsolutions.studentgrade.service;

import java.util.List;

import com.nixsolutions.studentgrade.entity.StudentGroup;

public interface StudentGroupService {

	public void createStudentGroup(StudentGroup group);

	public void updateStudentGroup(StudentGroup group);

	public void deleteStudentGroup(StudentGroup group);

	public StudentGroup findStudentGroupById(Long groupId);

	public List<StudentGroup> findAllStudentGroups();

}
