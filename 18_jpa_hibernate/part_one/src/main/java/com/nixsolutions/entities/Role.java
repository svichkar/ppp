package com.nixsolutions.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int role_id;
	@Column(name = "rolename", length = 255, nullable = false)
	private String rolename;

	public Role() {

	}

	public int getRoleId() {
		return role_id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRoleId(int value) {
		role_id = value;
	}

	public void setRolename(String value) {
		rolename = value;
	}

}
