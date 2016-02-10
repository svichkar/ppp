package com.nixsolutions;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 8640855383758736266L;
	
	private String firstName;
	private String lastName;
	private String address;
	private String occupation;
	private int age;

	public Person(String firstName, String lastName, String address, String occupation, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.occupation = occupation;
		this.age = age;
	}

	public Person() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", occupation="
				+ occupation + ", age=" + age + "]";
	}
}
