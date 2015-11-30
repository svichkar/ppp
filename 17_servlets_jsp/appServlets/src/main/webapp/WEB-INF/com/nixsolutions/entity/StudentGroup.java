package com.nixsolutions.entity;

import java.util.HashMap;
import java.util.Map;

public class StudentGroup {
    private int studentGroupId;
    private String groupName;
    private static Map<String, String> mapColNames;

    public StudentGroup() {
    }

    public StudentGroup(int studentGroupId, String groupName) {
	this.studentGroupId = studentGroupId;
	this.groupName = groupName;
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_studentGroupId", "student_group_id");
	    mapColNames.put("groupName", "group_name");
	}
    }

    public int getStudentGroupId() {
	return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
	this.studentGroupId = studentGroupId;
    }

    public String getGroupName() {
	return groupName;
    }

    public void setGroupName(String groupName) {
	this.groupName = groupName;
    }

    public static Map<String, String> getMap() {
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_studentGroupId", "student_group_id");
	    mapColNames.put("groupName", "group_name");
	}
	return mapColNames;
    }
}
