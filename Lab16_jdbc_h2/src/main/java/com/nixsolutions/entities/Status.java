package com.nixsolutions.entities;

public class Status extends BaseEntity {

	private int statusId;
	private String statusName;
	
	public Status(int statusId, String statusName){
		this.statusId = statusId;
		this.statusName = statusName;
	}

	@Override
	public int getId() {		
		return statusId;
	}
	
	public String getStatusName(){
		return statusName;
	}
	
	public void setId(int statusId) {		
		this.statusId = statusId;
	}
	
	public void setStatusName(String statusName){
		this.statusName = statusName;
	}
}
