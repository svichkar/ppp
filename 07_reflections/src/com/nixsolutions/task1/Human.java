package com.nixsolutions.task1;

public class Human {
    @Public
    private String firstName;
    private String lastName;

    public Human(String fn, String ln) {
	this.firstName = fn;
	this.setLastName(ln);
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }
}
