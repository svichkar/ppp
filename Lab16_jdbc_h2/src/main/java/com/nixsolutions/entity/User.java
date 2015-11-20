package com.nixsolutions.entity;

public class User extends BaseEntity {

	private int userId;
	private String userName;
	private String password;
	private String email;
	private int roleId;
	
	public User(int userId, String userName, String password, String email, int roleId){
		this.userId = userId;
		this.setUserName(userName);
		this.setPassword(password);
        this.setEmail(email);
        this.setRoleId(roleId);
	}
	@Override
	public int getId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
