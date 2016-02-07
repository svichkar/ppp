package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.StudentGroup;

import java.util.List;

/**
 * Created by konstantin on 1/28/2016.
 */
public interface StudentGroupService {

    public void create(StudentGroup group);

    public void update(StudentGroup group);

    public void delete(StudentGroup group);

    public List<StudentGroup> findAll();

    public StudentGroup findById(Long id);

    public StudentGroup findByName(String group);
}
