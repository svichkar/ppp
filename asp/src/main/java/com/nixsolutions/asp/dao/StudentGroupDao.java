package com.nixsolutions.asp.dao;

import com.nixsolutions.asp.entity.StudentGroup;

import java.util.List;

public interface StudentGroupDao {
	
	void create(StudentGroup studentGroup);

	void update(StudentGroup studentGroup);

	void delete(StudentGroup studentGroup);

	StudentGroup getByStudentGroupId(int studentGroupId);
	
	StudentGroup getByStudentGroupName(String studentGroupName);

	List<StudentGroup> getAll();
}
