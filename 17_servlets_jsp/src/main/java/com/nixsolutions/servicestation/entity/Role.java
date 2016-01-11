package com.nixsolutions.servicestation.entity;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
public class Role {
    private Integer roleId;
    private String roleName;

    public Role(){

    }

    public Role(String roleName, Integer roleId) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
