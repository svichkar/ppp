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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer specialization_id;
	
	@Column(name = "specialization_name", length = 50)
	private String specialization_name;

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
