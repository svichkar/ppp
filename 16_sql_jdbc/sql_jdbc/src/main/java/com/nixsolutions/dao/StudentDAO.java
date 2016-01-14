package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Student;

public interface StudentDAO {

	public void createStudent(Student student);

	public void updateStudent(Student student);

	public void deleteStudent(Student student);

	public Student findStudentById(Long studentId);

	public List<Student> findAllStudents();

}
