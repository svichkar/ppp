package com.nixsolutions.dao.impl;

import java.util.Date;
import java.util.List;

import com.nixsolutions.dao.StudentDao;
import com.nixsolutions.entity.Student;

public class StudentDaoImpl extends AbstractDaoImpl implements StudentDao {
    private static String TABLE_NAME = "student";

    @Override
    public Boolean create(Student student) {
	return super.insert(student, Student.getMap(), TABLE_NAME);
    }

    @Override
    public Boolean update(Student student) {
	return super.update(student, Student.getMap(), TABLE_NAME);
    }

    @Override
    public Boolean delete(Student student) {
	return super.delete("student_id", student.getStudentGroupId(), TABLE_NAME);
    }

    @Override
    public Student getStudentById(int studentId) {
	List<Student> studentList = super.find("student_group_id", String.valueOf(studentId), Student.getMap(),
		Student.class);
	if (studentList.size() > 0) {
	    return studentList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Student> getStudentsByName(String firstName, String lastName) {
	return super.findBySeveralFields(new String[] { "first_name", "last_name" },
		new String[] { firstName, lastName }, Student.getMap(), Student.class);
    }

    @Override
    public List<Student> getAll() {
	return super.find(null, null, Student.getMap(), Student.class);
    }

}
