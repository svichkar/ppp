package com.nixsolutions.studentgrade.service;

import java.sql.Date;
import java.util.List;

import com.nixsolutions.studentgrade.entity.Student;

public interface StudentService {

	public Long createStudent (String firstName, String lastName, Long groupId, Date admissionDate, Long statusId, Long termId);
	
	public void updateStudent(Long studentId, String firstName, String lastName, Long groupId, Date admissionDate, Long statusId, Long termId);

	public void deleteStudent(Long studentId);

	public Student findStudentById(Long studentId);	

	public List<Student> findAllStudents();
	
	public List<Student> findStudentsByLastNameAndGroupId (String lastName, Long groupId);
	
	public List<Student> findStudentsByLastName (String lastName);
	
	public List<Student> findStudentsByGroupId (Long groupId);	
	
}
