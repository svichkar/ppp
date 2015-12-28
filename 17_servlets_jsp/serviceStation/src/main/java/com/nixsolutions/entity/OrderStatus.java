package com.nixsolutions.entity;

public class OrderStatus {
	private Integer statusId;
	 private String statusName;
	 
	public OrderStatus(Integer statusId, String statusName) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer status_id) {
		this.statusId = status_id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String status_name) {
		this.statusName = status_name;
	}
	
	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", statusName=" + statusName + "]";
	}
	
	
}
