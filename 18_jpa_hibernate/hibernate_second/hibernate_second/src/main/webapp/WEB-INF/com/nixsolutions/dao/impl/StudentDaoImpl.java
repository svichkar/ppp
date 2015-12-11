package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.StudentDao;
import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.StudentGroup;

public class StudentDaoImpl extends AbstractDaoImpl implements StudentDao {

    @Override
    public Boolean create(Student student) {
	return super.insert(student);
    }

    @Override
    public Boolean update(Student student) {
	return super.update(student);
    }

    @Override
    public Boolean delete(Student student) {
	return super.delete(student);
    }

    @Override
    public Student getStudentById(Integer studentId) {
	Student s = new Student();
	s.setStudentId(studentId);
	List<Student> studentList = super.findBySeveralFields(s, new String[]{"studentId"});
	if (studentList.size() > 0) {
	    return studentList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Student> getStudentsByName(String firstName, String lastName) {
	Student s = new Student();
	s.setFirstName(firstName);
	s.setLastName(lastName);
	return super.findBySeveralFields(s, new String[]{"firstName","lastName"});
    }

    @Override
    public List<Student> getAll() {
	return super.findBySeveralFields(new Student(), null);
    }

    @Override
    public List<Student> getStudentsByGroup(StudentGroup group) {
	Student s = new Student();
	s.setStudentGroup(group);
	return super.findBySeveralFields(s, new String[]{"studentGroup"});
    }

}
