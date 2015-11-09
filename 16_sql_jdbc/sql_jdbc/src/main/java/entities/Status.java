package entities;

public class Status extends AbstractEntity {

	private int status_id;
	private String status_name;
	
	@Override
	public int getId() {
		return status_id;
	}

	@Override
	public String getValuesCommaSeparated() {
		return "'" + status_name + "'";
	}
	
	public String getStatusName() {
		return status_name;
	}
	
	protected void setId(int value) {
		status_id = value;
	}
	
	public void setStatusName(String value) {
		status_name = value;
	}
	
	@Override
	public String toString() {
		String res = "status_name = '" + status_name + "'";
		return res;
	}
	
	public Status(String status_name) {
		this.status_name = status_name;
	}
	
	public Status() {
		this("");
	}
}
