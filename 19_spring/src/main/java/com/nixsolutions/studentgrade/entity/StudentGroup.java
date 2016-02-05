package com.nixsolutions.studentgrade.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "student_group")
public class StudentGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Long groupId;
	@Column(name="group_name",  nullable = false)
	private String groupName;

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
