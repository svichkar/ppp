package com.nixsolutions.entity;

public class Worker {

	private int worker_id;
	private String first_name;
	private String last_name;
	private int specialization_id;
	private int status_id;
	
	public int getId() {
		return worker_id;
	}

	public String getValuesCommaSeparated() {
		return "'" + first_name + "', '" + last_name + "', " + 
				(specialization_id == 0 ? "null" : specialization_id) + ", " + 
				(status_id == 0 ? "null" : status_id);
	}
	
	public String getFirstName() {
		return first_name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public int getSpecId() {
		return specialization_id;
	}
	
	public int getStatusId() {
		return status_id;
	}
	
	public void setId(int value) {
		worker_id = value;
	}
	
	public void setFirstName(String value) {
		first_name = value;
	}
	
	public void setLastName(String value) {
		last_name = value;
	}
	
	public void setSpecializationId(int value) {
		specialization_id = value;
	}
	
	public void setStatusId(int value) {
		status_id = value;
	}
	
	@Override
	public String toString() {
		String res = "first_name = '" + first_name + "', last_name = '" + 
				last_name + "', specialization_id = " + 
				(specialization_id == 0 ? "null" : specialization_id) + 
				", status_id = " + (status_id == 0 ? "null" : status_id);
		return res;
	}
	
	public Worker(String first_name, String last_name, int specialization_id, int status_id) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.specialization_id = specialization_id;
		this.status_id = status_id;
	}
	
	public Worker() {
		this("", "", 0, 0);
	}
}
