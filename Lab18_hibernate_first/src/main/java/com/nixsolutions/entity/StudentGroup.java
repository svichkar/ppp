package com.nixsolutions.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class StudentGroup implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "student_group_id")
	private int studentGroupId;
    @Column(name = "student_group_name", length = 256, nullable = false)
	private String studentGroupName;
	
	public StudentGroup(){
	}

	public int getId() {		
		return studentGroupId;
	}
	
	public String getGroupName(){
		return studentGroupName;
	}

	public void setGroupName(String studentGroupName){
		this.studentGroupName = studentGroupName;
	}

}
