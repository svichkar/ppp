package database.entity;

public class Role extends BaseEntity {

	private int id;
	private String roleName;
	
	public Role(int roleId, String roleName){
		this.id = roleId;
		this.roleName = roleName;
	}
	@Override
	public int getId() {
		return id;
	}

	public void setId(int roleId) {
		this.id = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
