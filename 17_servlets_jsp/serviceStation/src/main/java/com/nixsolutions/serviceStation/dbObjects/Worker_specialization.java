package com.nixsolutions.serviceStation.dbObjects;

public class Worker_specialization {
	private Integer specialization_id;
	private String specialization_name;
	public Worker_specialization(Integer specialization_id, String specialization_name) {
		this.specialization_id = specialization_id;
		this.specialization_name = specialization_name;
	}
	public Integer getSpecialization_id() {
		return specialization_id;
	}
	public void setSpecialization_id(Integer specialization_id) {
		this.specialization_id = specialization_id;
	}
	public String getSpecialization_name() {
		return specialization_name;
	}
	public void setSpecialization_name(String specialization_name) {
		this.specialization_name = specialization_name;
	}
	

}
