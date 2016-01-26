package com.nixsolutions.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long role_id;
	@Column(name = "rolename", length = 255, nullable = false)
	private String rolename;

	public Role() {

	}

	public long getRoleId() {
		return role_id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRoleId(long value) {
		role_id = value;
	}

	public void setRolename(String value) {
		rolename = value;
	}

}
