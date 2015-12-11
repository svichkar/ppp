package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.entity.StudentGroup;
import com.nixsolutions.service.StudentGroupBo;

@Service
public class StudentGroupBoImpl implements StudentGroupBo {

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
