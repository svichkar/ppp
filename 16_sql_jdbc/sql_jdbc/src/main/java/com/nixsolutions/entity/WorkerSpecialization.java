package com.nixsolutions.entity;

public class WorkerSpecialization {

	private int specialization_id;
	private String specialization_name;
	
	public int getId() {
		return specialization_id;
	}

	public String getValuesCommaSeparated() {
		return "'" + specialization_name + "'";
	}
	
	public String getSpecName() {
		return specialization_name;
	}
	
	public void setId(int value) {
		specialization_id = value;
	}
	
	public void setSpecName(String value) {
		specialization_name = value;
	}
	
	@Override
	public String toString() {
		String res = "specialization_name = '" + specialization_name + "'";
		return res;
	}
	
	public WorkerSpecialization(String specName) {
		this.specialization_name = specName;
	}
	
	public WorkerSpecialization() {
		this("");
	}
}
