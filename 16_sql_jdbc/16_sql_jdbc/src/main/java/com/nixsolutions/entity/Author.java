package com.nixsolutions.entity;

public class Author {
	private int authorId;
	private String firstName;
	private String secondName;

	public Author(int authorId, String firstName, String secondName) {
		super();
		this.authorId = authorId;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
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
