package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Student;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface StudentDao {

    public boolean create(Student student);

    public int update(Student student);

    public int delete(Student student);

    public List<Student> findAll();

    public Student findById(int id);
}
