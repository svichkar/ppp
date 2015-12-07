package database.entities;

public class User extends BaseEntity {

	private int userId;
	private String userName;
	private String password;
	private int roleId;
	
	public User(int userId, String userName, String password, int roleId){
		this.userId = userId;
		this.setUserName(userName);
		this.setPassword(password);
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
