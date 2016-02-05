package com.nixsolutions.asp.service;

import com.nixsolutions.asp.entity.Student;

import java.util.List;

public interface StudentService {

	void create(Student student);

	void update(Student student);

	void delete(Student student);

	Student getByStudentId(int studentId);
	/**
	 * Search by first and last names
	 * @param firstName
	 * @param lastName
	 * @return list of students
	 */
	List<Student> getByStudentsByName(String firstName, String lastName);

    List<Student> getStudentsByGroupId(int studentGroupId);

	List<Student> getAll();
}
