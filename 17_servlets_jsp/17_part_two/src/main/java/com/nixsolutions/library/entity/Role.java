package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/28/2015.
 */
public class Role {
    private Integer roleId;
    private String name;

    public Role(int authorId, String name) {
        this.roleId = authorId;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
