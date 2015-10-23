package com.nixsolutions;

import java.beans.Transient;
import java.io.Serializable;

import javax.annotation.PreDestroy;

public class MyPreyClass implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nameOfPrey;
	private String description;
	private Integer ageOfPrey;

	public MyPreyClass(String name) {
		this.setName(name);
	}

	public String name() {
		return this.nameOfPrey;
	}

	public void setName(String name) {
		this.nameOfPrey = name;
	}

	public int age() {
		return this.ageOfPrey;
	}

	public void setAge(int age) {
		this.ageOfPrey = age;

	}

	@Transient
	public String description() {
		return this.description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

}
