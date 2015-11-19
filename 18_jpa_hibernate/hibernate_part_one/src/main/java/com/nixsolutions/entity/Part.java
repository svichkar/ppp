package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Part implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "part_id")
	private int part_id;
	@Column(name = "part_name", length = 250, nullable = false)
	private String part_name;
	@Column(name = "vendor", length = 100, nullable = false)
	private String vendor;
	@Column(name = "amount", nullable = false)
	private long amount;
	
	public void setPartId(int part_id) {
		this.part_id = part_id;
	}
}
