package com.nixsolutions.hibernate.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Author {
	
	@Id
	@Column
	private Long authorId;
	@Column
	private String firstName;
	@Column
	private String secondName;

	@ManyToMany
	@JoinTable(name = "author_book",
				joinColumns = {@JoinColumn(name = "AUTHOR_ID")},
				inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")})
	private Set<Book> books;
	
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
		return "author with Id: " + this.authorId + "; firstName: " + this.firstName + "; lastName: " + this.secondName;

	}

}
