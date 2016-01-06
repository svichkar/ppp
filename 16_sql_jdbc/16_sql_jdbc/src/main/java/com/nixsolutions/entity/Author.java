package com.nixsolutions.entity;

public class Author {
	private Integer authorId;
	private String firstName;
	private String secondName;

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	public String toString() {
		return "author with Id: " + this.authorId + "; firstName: " + this.firstName + "; lastName: " + this.secondName;

	}

}
