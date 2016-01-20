package com.nixsolutions.studentgrade.bean;

import com.nixsolutions.studentgrade.entity.User;
import com.nixsolutions.studentgrade.entity.Role;

public class UserBean {
	private User user;
	private Role role;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
