package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.StudentGroup;

public interface StudentDao {
    public Boolean create(Student student);

    public Boolean update(Student student);

    public Boolean delete(Student student);

    public Student getStudentById(Integer studentId);

    public List<Student> getStudentsByName(String firstName, String lastName);
    
    public List<Student> getStudentsByGroup(StudentGroup group);

    public List<Student> getAll();
}
