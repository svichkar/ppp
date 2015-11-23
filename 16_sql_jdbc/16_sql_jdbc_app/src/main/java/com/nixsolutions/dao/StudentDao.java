package com.nixsolutions.dao;

import java.util.Date;
import java.util.List;

import com.nixsolutions.entity.Student;

public interface StudentDao {
    public void create(String firstName, String lastName, Date dateBirthday, Date dateEntry, int studentGroupId,
	    int termId, int statusId);

    public void update(Student student);

    public void delete(Student student);

    public Student getStudentById(int studentId);

    public List<Student> getStudentsByName(String firstName, String lastName);

    public List<Student> getAll();
}
