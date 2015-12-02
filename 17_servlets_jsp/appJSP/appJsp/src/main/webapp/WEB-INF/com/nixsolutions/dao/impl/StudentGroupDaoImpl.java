package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.StudentGroupDao;
import com.nixsolutions.entity.StudentGroup;

public class StudentGroupDaoImpl extends AbstractDaoImpl implements StudentGroupDao {
    private static String TABLE_NAME = "studentgroup";

    @Override
    public Boolean create(StudentGroup studentGroup) {
	return super.insert(studentGroup, StudentGroup.getMap(), TABLE_NAME);
    }

    @Override
    public Boolean update(StudentGroup studentGroup) {
	return super.update(studentGroup, StudentGroup.getMap(), TABLE_NAME);
    }

    @Override
    public Boolean delete(StudentGroup studentGroup) {
	return super.delete("student_group_id", studentGroup.getStudentGroupId(), TABLE_NAME);
    }

    @Override
    public StudentGroup getStudentGroupById(int studentGroupId) {
	List<StudentGroup> studentGroupList = super.find("student_group_id", String.valueOf(studentGroupId), StudentGroup.getMap(), StudentGroup.class);
	if (studentGroupList.size() > 0) {
	    return studentGroupList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public StudentGroup getStudentGroupByName(String studentGroupName) {
	List<StudentGroup> studentGroupList = super.find("group_name", String.valueOf(studentGroupName), StudentGroup.getMap(), StudentGroup.class);
	if (studentGroupList.size() > 0) {
	    return studentGroupList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<StudentGroup> getAll() {
	return super.find(null, null, StudentGroup.getMap(), StudentGroup.class);
    }
    
}
