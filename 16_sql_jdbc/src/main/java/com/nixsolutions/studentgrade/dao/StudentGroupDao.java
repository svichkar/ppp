package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.StudentGroup;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface StudentGroupDao {

    public StudentGroup create(StudentGroup group);

    public boolean update(StudentGroup group);

    public boolean delete(StudentGroup group);

    public List<StudentGroup> findAll();

    public StudentGroup findById(int id);
}
