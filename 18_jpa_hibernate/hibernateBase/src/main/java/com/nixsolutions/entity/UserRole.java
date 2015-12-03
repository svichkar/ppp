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

	/**
	 * 
	 */
	private static final long serialVersionUID = -2374585738250492459L;

	@Id
	@Column(name = "user_role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_role_id;

	@Column(name = "user_role_name", unique = true)
	private String user_role_name;

	public Integer getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(Integer user_role_id) {
		this.user_role_id = user_role_id;
	}

	public String getUser_role_name() {
		return user_role_name;
	}

	public void setUser_role_name(String user_role_name) {
		this.user_role_name = user_role_name;
	}

}
