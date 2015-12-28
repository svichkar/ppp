/**
 * 
 */
package com.nixsolutions.entity;

public class User {

	private Integer userId;
	private String userLogin;
	private String userPassword;
	private Integer userRoleId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userLogin=" + userLogin + ", userPassword=" + userPassword
				+ ", userRoleId=" + userRoleId + "]";
	}
}
