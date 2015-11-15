package com.nixsolutions.entities;

public class StudentGroup extends BaseEntity {

	private int studentGroupId;
	private String studentGroupName;
	
	public StudentGroup(int studentGroupId, String studentGroupName){
		this.studentGroupId = studentGroupId;
		this.studentGroupName = studentGroupName;
	}

	@Override
	public int getId() {		
		return studentGroupId;
	}
	
	public String getGroupName(){
		return studentGroupName;
	}
	
	public void setId(int studentGroupId) {		
		this.studentGroupId = studentGroupId;
	}
	
	public void setGroupName(String studentGroupName){
		this.studentGroupName = studentGroupName;
	}

}
