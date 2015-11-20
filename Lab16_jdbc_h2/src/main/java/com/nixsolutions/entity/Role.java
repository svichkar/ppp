package com.nixsolutions.entity;

public class Role extends BaseEntity {

	private int roleId;
	private String roleName;
	
	public Role(int roleId, String roleName){
		this.roleId = roleId;
		this.roleName = roleName;
	}
	@Override
	public int getId() {
		return roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
