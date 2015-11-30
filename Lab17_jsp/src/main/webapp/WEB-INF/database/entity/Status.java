package database.entity;

public class Status extends BaseEntity {

	private int id;
	private String statusName;
	
	public Status(int statusId, String statusName){
		this.id = statusId;
		this.statusName = statusName;
	}

	@Override
	public int getId() {		
		return id;
	}
	
	public String getStatusName(){
		return statusName;
	}
	
	public void setId(int statusId) {		
		this.id = statusId;
	}
	
	public void setStatusName(String statusName){
		this.statusName = statusName;
	}
}
