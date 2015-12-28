package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.StudentGroup;

public interface StudentGroupDAO {

	public StudentGroup createStudentGroup(int groupId, String groupName);

	public void updateStudentGroup(StudentGroup group);

	public void deleteStudentGroup(StudentGroup group);

	public StudentGroup findStudentGroupById(int groupId);

	public List<StudentGroup> findAllStudentGroups();

}
