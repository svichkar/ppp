package com.nixsolutions.studentgrade.entity;

public class Role {
	private long roleId;
	private String roleName;

	public Role(long roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Role() {
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
