package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.entity.StudentGroup;

public class StudentGroupDaoImpl extends AbstractDaoImpl implements StudentGroupDao {

    @Override
    public Boolean create(StudentGroup studentGroup) {
	return super.insert(studentGroup);
    }

    @Override
    public Boolean update(StudentGroup studentGroup) {
	return super.update(studentGroup);
    }

    @Override
    public Boolean delete(StudentGroup studentGroup) {
	return super.delete(studentGroup);
    }

    @Override
    public StudentGroup getStudentGroupById(Integer studentGroupId) {
	StudentGroup sg = new StudentGroup();
	sg.setStudentGroupId(studentGroupId);
	List<StudentGroup> studentGroupList = super.findBySeveralFields(sg, new String[]{"studentGroupId"});
	if (studentGroupList.size() > 0) {
	    return studentGroupList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public StudentGroup getStudentGroupByName(String studentGroupName) {
	StudentGroup sg = new StudentGroup();
	sg.setGroupName(studentGroupName);;
	List<StudentGroup> studentGroupList = super.findBySeveralFields(sg, new String[]{"groupName"});
	if (studentGroupList.size() > 0) {
	    return studentGroupList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<StudentGroup> getAll() {
	return super.findBySeveralFields(new StudentGroup(), null);
    }
    
}
