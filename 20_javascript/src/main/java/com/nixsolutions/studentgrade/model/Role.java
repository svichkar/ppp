package com.nixsolutions.studentgrade.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by svichkar on 12/18/2015.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @Column(name = "role_id", nullable = false, unique = true)
    private Long roleId;

    @Column(name = "role_name", nullable = false, unique = true, length = 256)
    private String roleName;

    public Role() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (!roleId.equals(role.roleId)) return false;
        return roleName.equals(role.roleName);

    }

    @Override
    public int hashCode() {
        int result = roleId.hashCode();
        result = 31 * result + roleName.hashCode();
        return result;
    }
}
