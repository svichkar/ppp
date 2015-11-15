package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entities.StudentGroup;

public interface StudentGroupDao {

	
	public void create(String studentGroupName);

	public void update(StudentGroup studentGroup);

	public void delete(StudentGroup studentGroup);

	public StudentGroup getByStudentGroupId(int studentGroupId);
	
	public StudentGroup getByStudentGroupName(String studentGroupName);

	public List<StudentGroup> getAll();
}
