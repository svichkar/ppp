package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reader implements Serializable {
	public Reader() {
	}
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 100)
	private Integer id;
	@Column(name = "name", length = 50)
	private String name;
	@Column(name = "adress", length = 50)
	private String adress;
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}

}
