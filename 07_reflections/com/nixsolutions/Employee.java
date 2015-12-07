package com.nixsolutions;

public class Employee {
	@ToString
	private String name;
	@ToString
	private int age;
	@ToString
	private int department;
	@ToString
	private double salary;

	public Employee(String name, int age, int department, double salary) {
		this.name = name;
		this.age = age;
		this.department = department;
		this.salary = salary;
	}

}
