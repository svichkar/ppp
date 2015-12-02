package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Student;

public interface StudentDao {
    public Boolean create(Student student);

    public Boolean update(Student student);

    public Boolean delete(Student student);

    public Student getStudentById(int studentId);

    public List<Student> getStudentsByName(String firstName, String lastName);
    
    public List<Student> getStudentsByGroup(int groupId);

    public List<Student> getAll();
}
