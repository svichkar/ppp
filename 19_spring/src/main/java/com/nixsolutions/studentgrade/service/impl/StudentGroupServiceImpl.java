package com.nixsolutions.studentgrade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.studentgrade.dao.StudentGroupDAO;
import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.service.StudentGroupService;

@Service
@Transactional
public class StudentGroupServiceImpl implements StudentGroupService {

	@Autowired
	StudentGroupDAO groupDao;

	@Override
	public void createStudentGroup(StudentGroup group) {
		groupDao.createStudentGroup(group);
	}

	@Override
	public void updateStudentGroup(StudentGroup group) {
		groupDao.updateStudentGroup(group);
	}

	@Override
	public void deleteStudentGroup(StudentGroup group) {
		groupDao.deleteStudentGroup(group);
	}

	@Override
	public StudentGroup findStudentGroupById(Long groupId) {
		return groupDao.findStudentGroupById(groupId);
	}

	@Override
	public List<StudentGroup> findAllStudentGroups() {
		return groupDao.findAllStudentGroups();
	}

}
