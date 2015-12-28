package com.nixsolutions.dao;

import java.sql.Date;
import java.util.List;

import com.nixsolutions.entity.Student;

public interface StudentDAO {

	public Student createStudent(int studentId, String firstName, String lastName, int groupId, Date admissionDate, int statusId,
			int termId);

	public void updateStudent(Student student);

	public void deleteStudent(Student student);

	public Student findStudentById(int studentId);

	public List<Student> findAllStudents();

}
