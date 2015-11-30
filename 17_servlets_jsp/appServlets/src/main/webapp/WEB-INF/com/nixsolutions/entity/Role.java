package com.nixsolutions.entity;

import java.util.HashMap;
import java.util.Map;

public class Role {
    private int roleId;
    private String roleName;
    private static Map<String, String> mapColNames;

    public Role() {
    }

    public Role(int roleId, String roleName) {
	this.roleId = roleId;
	this.roleName = roleName;
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_roleId", "role_id");
	    mapColNames.put("roleName", "role");
	}
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

    public static Map<String, String> getMap() {
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_roleId", "role_id");
	    mapColNames.put("roleName", "role");
	}
	return mapColNames;
    }
}
