package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "role_name", length = 256, nullable = false)
    private String roleName;

    public Role() {
    }

    public Integer getRoleId() {
	return roleId;
    }

    public void setRoleId(Integer roleId) {
	this.roleId = roleId;
    }

    public String getRoleName() {
	return roleName;
    }

    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }
}
