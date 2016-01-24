package com.nixsolutions.entities;

public class Role extends BaseEntity {

	private int role_id;
	private String rolename;

	public Role() {

	}

	public Role(int role_id, String rolename) {
		this.role_id = role_id;
		this.rolename = rolename;
	}

	@Override
	public int getId() {
		return role_id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setId(int value) {
		role_id = value;
	}

	public void setRolename(String value) {
		rolename = value;
	}

}
