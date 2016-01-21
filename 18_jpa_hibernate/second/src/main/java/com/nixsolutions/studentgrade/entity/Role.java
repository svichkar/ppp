package com.nixsolutions.studentgrade.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by svichkar on 12/18/2015.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
