package com.nixsolutions.entity;

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
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	public User() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -821139650412781348L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;

	@Column(name = "user_login", length = 20, nullable = false, unique = true)
	private String userLogin;
	
	@Column(name = "user_password", length = 20, nullable = false)
	private String userPassword;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id")
	private UserRole userRole;

	public Long getUser_id() {
		return userId;
	}

	public void setUser_id(Long user_id) {
		this.userId = user_id;
	}

	public String getUser_login() {
		return userLogin;
	}

	public void setUser_login(String user_login) {
		this.userLogin = user_login;
	}

	public String getUser_password() {
		return userPassword;
	}

	public void setUser_password(String user_password) {
		this.userPassword = user_password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
