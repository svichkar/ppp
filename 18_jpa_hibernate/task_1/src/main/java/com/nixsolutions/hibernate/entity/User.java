package com.nixsolutions.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class User {
	
	@Id
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
	private Role role;

	public User(String userName, String userPassword, Role role) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.role = role;
	}

	public User(String userName, String userPassword, Role role, Long userId) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.role = role;
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRoleId(Role role) {
		this.role = role;
	}

	public String toString() {
		return "[User with userId: " + this.userId + "; name: " + this.userName + "; password: " + this.userPassword
				+ "; role: " + this.role + "]";

	}
}
