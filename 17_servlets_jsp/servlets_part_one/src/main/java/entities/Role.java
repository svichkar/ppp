package entities;

public class Role extends AbstractEntity {

	private int role_id;
	private String role_name;
	
	@Override
	public int getId() {
		return role_id;
	}
	
	public String getRoleName() {
		return role_name;
	}
	
	protected void setId(int value) {
		role_id = value;
	}
	
	public void setRoleName(String value) {
		role_name = value;
	}

	@Override
	public String getValuesCommaSeparated() {
		return role_name;
	}
	
	@Override
	public String toString() {
		String res = "role_name = '" + role_name + "'";
		return res;
	}
	
	public Role(String role_name) {
		this.role_name = role_name;
	}
	
	public Role() {
		this("");
	}
}
