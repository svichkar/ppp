package com.nixsolutions.asp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.asp.dao.StudentGroupDao;
import com.nixsolutions.asp.entity.StudentGroup;
import com.nixsolutions.asp.service.StudentGroupService;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

	@Autowired
	private StudentGroupDao studentGroupDao;
	
	@Override
	public void create(StudentGroup studentGroup) {
		studentGroupDao.create(studentGroup);
	}

	@Override
	public void update(StudentGroup studentGroup) {
		studentGroupDao.update(studentGroup);
	}

	@Override
	public void delete(StudentGroup studentGroup) {
		studentGroupDao.delete(studentGroup);
	}

	@Override
	public StudentGroup getByStudentGroupId(int studentGroupId) {
		return studentGroupDao.getByStudentGroupId(studentGroupId);
	}

	@Override
	public StudentGroup getByStudentGroupName(String studentGroupName) {
		return studentGroupDao.getByStudentGroupName(studentGroupName);
	}

	@Override
	public List<StudentGroup> getAll() {
		return studentGroupDao.getAll();
	}

}
