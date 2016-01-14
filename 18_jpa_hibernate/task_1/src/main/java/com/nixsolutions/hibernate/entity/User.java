package com.nixsolutions.hibernate.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	private Long userId;
	@NotNull
	@Size(min = 3, max = 10)
	@Column(name = "USER_NAME")
	private String userName;
	@NotNull
	@Size(min = 4, max = 12)
	@Column(name = "USER_PASSWORD")
	private String userPassword;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	private Role role;

	public User() {
	}

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
		return "[User with userId: " + this.userId + "; name: " + this.userName + "; password: "
				+ this.userPassword + "; role: " + this.role + "]";

	}
}
