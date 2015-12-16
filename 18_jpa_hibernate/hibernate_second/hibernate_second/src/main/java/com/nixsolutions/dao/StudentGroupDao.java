package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.StudentGroup;

public interface StudentGroupDao {

    public Boolean create(StudentGroup studentGroup);

    public Boolean update(StudentGroup studentGroup);

    public Boolean delete(StudentGroup studentGroup);

    public StudentGroup getStudentGroupById(Integer studentGroupId);

    public StudentGroup getStudentGroupByName(String studentGroupName);

    public List<StudentGroup> getAll();
}
