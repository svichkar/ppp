package com.nixsolutions.entity;

public class Author {
	private Integer authorId;
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

}
