package com.nixsolutions.studentgrade.entity;

public class StudentGroup {
	private int groupId;
	private String groupName;

	public StudentGroup(int groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public StudentGroup() {
		this(0, "default");
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
