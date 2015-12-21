package com.nixsolutions.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "worker_specialization")
public class WorkerSpecialization implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "specialization_id")
	private Integer specializationId;
	@Column(name = "specialization_name", length = 50, nullable = false)
	private String specializationName;

	public Integer getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Integer specializationId) {
		this.specializationId = specializationId;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	public WorkerSpecialization(String specializationName) {
		this.specializationName = specializationName;
	}
	
	public WorkerSpecialization() {
		this("");
	}
}
