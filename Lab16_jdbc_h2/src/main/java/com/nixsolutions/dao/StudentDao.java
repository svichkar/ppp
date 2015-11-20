package com.nixsolutions.dao;

import java.sql.Date;
import java.util.List;

import com.nixsolutions.entity.Student;

public interface StudentDao {

	public void create(String firstName, String lastName, Date dateBirthday, Date dateEntry,
			int studentGroupId, int termId, int statusId);

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

	public List<Student> getAll();
}
