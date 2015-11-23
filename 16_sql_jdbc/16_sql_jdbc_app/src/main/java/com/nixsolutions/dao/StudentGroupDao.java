package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.StudentGroup;

public interface StudentGroupDao {

    public void create(String studentGroupName);

    public void update(StudentGroup studentGroup);

    public void delete(StudentGroup studentGroup);

    public StudentGroup getStudentGroupById(int studentGroupId);

    public StudentGroup getStudentGroupByName(String studentGroupName);

    public List<StudentGroup> getAll();
}
