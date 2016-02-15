package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Student;

import java.util.List;

/**
 * Created by svichkar on 1/29/2016.
 */
public interface StudentService {

    void create(Student student);

    void update(Student student);

    void delete(Student student);

    List<Student> findAll();

    Student findById(Long id);

    Student findByNameAndLastName(String name, String lastName);

    List<Student> findByLastNameAndGroup(String lastName, String groupName);
}
