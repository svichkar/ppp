package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.StudentGroup;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface StudentGroupDao {

    public void create(StudentGroup group);

    public void update(StudentGroup group);

    public void delete(StudentGroup group);

    public List<StudentGroup> findAll();

    public StudentGroup findById(Long id);

    public StudentGroup findByName(String group);
}
