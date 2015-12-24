package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.StudentDao;
import com.nixsolutions.entity.Student;
import com.nixsolutions.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public void create(Student student) {
		studentDao.create(student);
	}

	@Override
	public void update(Student student) {
		studentDao.update(student);
	}

	@Override
	public void delete(Student student) {
		studentDao.delete(student);
	}

	@Override
	public Student getByStudentId(int studentId) {
		return studentDao.getByStudentId(studentId);
	}

	@Override
	public List<Student> getByStudentsByName(String firstName, String lastName) {
		return studentDao.getByStudentsByName(firstName, lastName);
	}

	@Override
	public List<Student> getStudentsByGroupId(int studentGroupId) {
		return studentDao.getStudentsByGroupId(studentGroupId);
	}

	@Override
	public List<Student> getAll() {
		return studentDao.getAll();
	}

}
