package com.nixsolutions.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roleId;
	@Column(name = "rolename", length = 255, nullable = false)
	private String roleName;

	public Role() {

	}

	public long getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleId(long value) {
		roleId = value;
	}

	public void setRoleName(String value) {
		roleName = value;
	}

}
