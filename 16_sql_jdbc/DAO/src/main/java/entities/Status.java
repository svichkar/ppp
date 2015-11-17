package entities;

public class Status extends BaseEntity {

	private int status_id;
	private String status_name;

	public Status(int status_id, String status_name) {
		this.status_id = status_id;
		this.status_name = status_name;
	}
	
	public Status() {
		
	}


	public int getId() {
		return status_id;
	}

	public String getStatus_name() {
		return status_name;
	}

	protected void setId(int value) {
		status_id = value;
	}

	public void setStatus_name(String value) {
		status_name = value;
	}

}
