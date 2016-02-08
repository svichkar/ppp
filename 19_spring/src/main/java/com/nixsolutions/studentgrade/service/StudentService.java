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

    List<Student> findByLastName(String lastName);

    List<Student> findByGroupId(Long groupId);

    List<Student> findByLastNameAndGroupId(String lastName, Long groupId);

    List<Student> findByLastNameAndGroup(String lastName, String groupName);

    List<Student> findByGroup(String groupName);

    boolean isExist(String firstName, String lastName);
}
