package com.nixsolutions.entity;

public class Author {
	private Long authorId;
	private String firstName;
	private String secondName;

	/*
	public Author(int authorId, String firstName, String secondName) {
		super();
		this.authorId = authorId;
		this.firstName = firstName;
		this.secondName = secondName;
	}
*/

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
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
		return this.firstName + " " + this.secondName;

	}

}
