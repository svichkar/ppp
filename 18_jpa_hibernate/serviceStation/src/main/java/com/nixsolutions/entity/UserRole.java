/**
 * 
 */
package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mixeyes
 *
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

	public UserRole() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2374585738250492459L;

	@Id
	@Column(name = "user_role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userRoleId;

	@Column(name = "user_role_name", unique = true)
	private String userRoleName;

	public Integer getUser_role_id() {
		return userRoleId;
	}

	public void setUser_role_id(Integer user_role_id) {
		this.userRoleId = user_role_id;
	}

	public String getUser_role_name() {
		return userRoleName;
	}

	public void setUser_role_name(String user_role_name) {
		this.userRoleName = user_role_name;
	}
}
