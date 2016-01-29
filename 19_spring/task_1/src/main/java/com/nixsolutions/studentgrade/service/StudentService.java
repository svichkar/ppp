package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Student;

import java.util.List;

/**
 * Created by svichkar on 1/29/2016.
 */
public interface StudentService {

    public void create(Student student);

    public void update(Student student);

    public void delete(Student student);

    public List<Student> findAll();

    public Student findById(Long id);

    public Student findByNameAndLastName(String name, String lastName);

    public List<Student> findByLastName(String lastName);

    public List<Student> findByGroupId(Long groupId);

    public List<Student> findByLastNameAndGroupId(String lastName, Long groupId);
}
