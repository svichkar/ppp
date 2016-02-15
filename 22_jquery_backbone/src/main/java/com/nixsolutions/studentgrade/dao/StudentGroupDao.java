package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.StudentGroup;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface StudentGroupDao {

    void create(StudentGroup group);

    void update(StudentGroup group);

    void delete(StudentGroup group);

    List<StudentGroup> findAll();

    StudentGroup findById(Long id);

    StudentGroup findByName(String group);
}
