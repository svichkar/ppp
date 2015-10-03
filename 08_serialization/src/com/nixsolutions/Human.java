package com.nixsolutions;

import java.io.Serializable;

public class Human implements Serializable {

    String firstName;
    String lastName;
    int age;

    public Human(String fName, String lName, int age) {
	this.firstName = fName;
	this.lastName = lName;
	this.age = age;
    }

    @Override
    public String toString() {
	return firstName + " " + lastName + " " + age;
    }
}
