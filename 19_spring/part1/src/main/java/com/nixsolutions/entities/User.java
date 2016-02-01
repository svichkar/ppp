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
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;
	@Column(name = "username", nullable = false, length = 255)
	private String username;
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	@Column(name = "session_id", length = 255)
	private String session_id;
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
		return user_id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getSession_id() {
		return session_id;
	}

	public Role getRole() {
		return role;
	}
	

	public void setUserId(long value) {
		this.user_id = value;
	}

	public void setUsername(String value) {
		this.username = value;
	}

	public void setPassword(String value) {
		this.password = value;
	}
	
	public void setSession(String value)
	{
		this.session_id = value;
	}

	public void setRole(Role value) {
		this.role = value;
	}

}
