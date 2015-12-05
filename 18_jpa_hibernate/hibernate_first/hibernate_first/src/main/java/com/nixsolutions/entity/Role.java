package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "role_name", length = 256, nullable = false)
    private String roleName;

    public Role() {
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
