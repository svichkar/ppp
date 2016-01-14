package com.nixsolutions.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private long roleId;
	@Column(name="role_name",  nullable = false)
	private String roleName;

	public Role() {
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
