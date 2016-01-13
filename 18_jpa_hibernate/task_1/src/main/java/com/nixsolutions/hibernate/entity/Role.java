package com.nixsolutions.hibernate.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Role {
	
	@Id
	@Column(name = "ROLE_ID")
	private Long roleId;
	@Column(name = "ROLE_NAME")
	private String name;
	
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	List<User> users;
	
	public Role(String string) {
		name = string;
	}
	
	public Long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "[Role with name: " + this.name + "; role id: " + this.roleId + "]";	
	}
}
