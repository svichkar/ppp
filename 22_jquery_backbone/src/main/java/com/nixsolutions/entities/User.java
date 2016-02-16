package com.nixsolutions.entities;

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

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	@Column(name = "username", nullable = false, length = 255)
	private String username;
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	@Column(name = "session_id", length = 255)
	private String sessionId;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private Role role;
	
	public User() {
	}

	public User(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public Role getRole() {
		return role;
	}
	

	public void setUserId(long value) {
		this.userId = value;
	}

	public void setUsername(String value) {
		this.username = value;
	}

	public void setPassword(String value) {
		this.password = value;
	}
	
	public void setSessionId(String value)
	{
		this.sessionId = value;
	}

	public void setRole(Role value) {
		this.role = value;
	}

}
