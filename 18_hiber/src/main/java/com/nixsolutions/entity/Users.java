package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Users implements Serializable {
	public Users() {
	}
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 10)
	private Integer id;
	@Column(name = "login", length = 50, nullable = false)
	private String login;
	@Column(name = "password", length = 50, nullable = false)
	private String password;
	@Column(name = "role", length = 50, nullable = false)
	private String role;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
