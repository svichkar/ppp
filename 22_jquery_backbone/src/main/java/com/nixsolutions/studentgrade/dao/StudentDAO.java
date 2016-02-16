package com.nixsolutions.studentgrade.dao;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Student;

public interface StudentDAO {

	public void createStudent(Student student);

	public void updateStudent(Student student);

	public void deleteStudent(Student student);

	public Student findStudentById(Long studentId);	

	public List<Student> findAllStudents();
	
	public List<Student> findStudentsByLastNameAndGroupId (String lastName, Long groupId);
	
	public List<Student> findStudentsByLastName (String lastName);
	
	public List<Student> findStudentsByGroupId (Long groupId);
	

}
