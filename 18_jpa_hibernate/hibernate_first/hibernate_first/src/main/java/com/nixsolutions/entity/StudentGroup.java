package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class StudentGroup implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "student_group_id")
    private int studentGroupId;
    @Column(name = "group_name", length = 256, nullable = false)
    private String groupName;

    public StudentGroup() {
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
}
