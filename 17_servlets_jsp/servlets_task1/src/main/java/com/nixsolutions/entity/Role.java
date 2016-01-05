package com.nixsolutions.entity;

public class Role {
	private Integer roleId;
	private String name;
	
	public Role(String string) {
		name = string;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
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
