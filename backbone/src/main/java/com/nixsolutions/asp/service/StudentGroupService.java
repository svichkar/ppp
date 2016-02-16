package com.nixsolutions.asp.service;

import com.nixsolutions.asp.entity.StudentGroup;

import java.util.List;

public interface StudentGroupService {
	
	void create(StudentGroup studentGroup);

	void update(StudentGroup studentGroup);

	void delete(StudentGroup studentGroup);

	StudentGroup getByStudentGroupId(int studentGroupId);
	
	StudentGroup getByStudentGroupName(String studentGroupName);

	List<StudentGroup> getAll();
}
