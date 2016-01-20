package com.nixsolutions.studentgrade.entity;

public class StudentGroup {
	private Long groupId;
	private String groupName;

	public StudentGroup(Long groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public StudentGroup() {
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
