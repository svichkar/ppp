package com.nixsolutions.firsttask;

public class Person {
	@ToString
	private String firstName;
	private String lastName;
	private String occupation;
	@ToString
	private int age;

	public Person(String firstName, String lastName, String occupation, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
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
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", occupation=" + occupation + ", age="
				+ age + "]";
	}

}
