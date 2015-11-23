package com.nixsolutions.dao.impl;

import java.util.Date;
import java.util.List;

import com.nixsolutions.dao.StudentDao;
import com.nixsolutions.entity.Student;

public class StudentDaoImpl extends AbstractDaoImpl implements StudentDao {
    private static String TABLE_NAME = "student";

    @Override
    public void create(String firstName, String lastName, Date dateBirthday, Date dateEntry, int studentGroupId,
	    int termId, int statusId) {
	super.insert(
		new String[] { "first_name", "last_name", "date_birthday", "date_entry", "student_group_id", "term_id",
			"status_id" },
		new String[] { firstName, lastName, String.valueOf(dateBirthday), String.valueOf(dateEntry),
			String.valueOf(studentGroupId), String.valueOf(termId), String.valueOf(statusId) },
		TABLE_NAME);
    }

    @Override
    public void update(Student student) {
	super.update(student, Student.getMap(), TABLE_NAME);
    }

    @Override
    public void delete(Student student) {
	super.delete("student_id", student.getStudentGroupId(), TABLE_NAME);
    }

    @Override
    public Student getStudentById(int studentId) {
	List<Student> studentList = super.find("student_group_id", String.valueOf(studentId), Student.getMap(), Student.class);
	if (studentList.size() > 0) {
	    return studentList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Student> getStudentsByName(String firstName, String lastName) {
	return super.findBySeveralFields(new String[] {"first_name", "last_name"}, new String[] {firstName, lastName}, Student.getMap(), Student.class);
    }

    @Override
    public List<Student> getAll() {
	return super.find(null, null, Student.getMap(), Student.class);
    }

}
