package com.nixsolutions.studentgrade.entity;

/**
 * Created by svichkar on 12/18/2015.
 */
public class Role {

    private int roleId;
    private int roleName;

    public Role() {
    }

    public Role(int roleId, int roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleName() {
        return roleName;
    }

    public void setRoleName(int roleName) {
        this.roleName = roleName;
    }
}
