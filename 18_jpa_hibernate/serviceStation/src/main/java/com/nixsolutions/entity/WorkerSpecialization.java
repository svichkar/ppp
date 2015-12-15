package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "worker_specialization")
public class WorkerSpecialization implements Serializable{

	public WorkerSpecialization() {
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="specialization_id")
	private Integer specializationId;
	
	@Column(name = "specialization_name", length = 50)
	private String specializationName;

	public Integer getSpecialization_id() {
		return specializationId;
	}
	public void setSpecialization_id(Integer specialization_id) {
		this.specializationId = specialization_id;
	}
	public String getSpecialization_name() {
		return specializationName;
	}
	public void setSpecialization_name(String specialization_name) {
		this.specializationName = specialization_name;
	}

}
