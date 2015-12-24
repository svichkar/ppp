package com.nixsolutions.service;

import com.nixsolutions.entity.StudentGroup;

import java.util.List;

public interface StudentGroupService {

	
	public void create(StudentGroup studentGroup);

	public void update(StudentGroup studentGroup);

	public void delete(StudentGroup studentGroup);

	public StudentGroup getByStudentGroupId(int studentGroupId);
	
	public StudentGroup getByStudentGroupName(String studentGroupName);

	public List<StudentGroup> getAll();
}
