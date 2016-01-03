package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Student;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface StudentDao {

    public Student create(Student student);

    public boolean update(Student student);

    public boolean delete(Student student);

    public List<Student> findAll();

    public Student findById(int id);
}
