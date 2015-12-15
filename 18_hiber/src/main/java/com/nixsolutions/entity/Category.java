package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category implements Serializable{

	public Category() {
	}
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 3)
	private Integer id;
	@Column(name = "name", unique = true, length = 40)
	private String name;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
