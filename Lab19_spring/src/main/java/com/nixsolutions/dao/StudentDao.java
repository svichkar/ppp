package com.nixsolutions.dao;

import com.nixsolutions.entity.Student;
import com.nixsolutions.entity.StudentGroup;

import java.util.List;

public interface StudentDao {

	public void create(Student student);

	public void update(Student student);

	public void delete(Student student);

	public Student getByStudentId(int studentId);
	/**
	 * Search by first and last names
	 * @param firstName
	 * @param lastName
	 * @return list of students
	 */
	public List<Student> getByStudentsByName(String firstName, String lastName);

    public List<Student> getStudentsByGroup(StudentGroup studentGroup);

	public List<Student> getAll();
}
