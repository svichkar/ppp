package com.nixsolutions.annotation;

public class VictimClass {

	@ToString
	public String nameOfVictim;
	@ToString
	public Double weightOfVictim;
	@ToString
	public Integer ageOfVictim;

	@ToString
	private Integer id;

	public VictimClass() {
		//set default values
		this.nameOfVictim = "John";
		this.ageOfVictim = 19;
		this.weightOfVictim = 60.0;
		this.id = 1;
	}
	
	public VictimClass(Integer id, String name, Double weight, Integer age) {
		this.id = id;
		this.nameOfVictim = name;
		this.weightOfVictim = weight;
		this.ageOfVictim = age;
	}

	@Override
	public String toString() {
		return this.nameOfVictim;
	}
}
