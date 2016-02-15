package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.StudentGroup;

import java.util.List;

/**
 * Created by konstantin on 1/28/2016.
 */
public interface StudentGroupService {

    void create(StudentGroup group);

    void update(StudentGroup group);

    void delete(StudentGroup group);

    List<StudentGroup> findAll();

    StudentGroup findById(Long id);

    StudentGroup findByName(String group);
}
