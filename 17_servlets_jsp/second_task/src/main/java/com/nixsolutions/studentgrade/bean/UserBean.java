package com.nixsolutions.studentgrade.bean;

import com.nixsolutions.studentgrade.entity.User;
import com.nixsolutions.studentgrade.entity.Role;

public class UserBean {
	private Long userId;
	private String login;
	private String email;
	private String password;
	private String roleName;

	public UserBean() {

	}

	public UserBean(User user, Role role) {
		userId = user.getUserId();
		login = user.getLogin();
		email = user.getEmail();
		password = user.getPassword();
		roleName = role.getRoleName();
	}

	public Long getUserId() {
		return userId;
	}

	public String getLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRoleName() {
		return roleName;
	}
}
