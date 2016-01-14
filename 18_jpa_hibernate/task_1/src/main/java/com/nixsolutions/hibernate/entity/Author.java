package com.nixsolutions.hibernate.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Author implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUTHOR_ID", unique = true, nullable = false)
	private Long authorId;
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "LAST_NAME", nullable = false)
	private String secondName;

	@ManyToMany
	@JoinTable(name = "author_book", joinColumns = { @JoinColumn(name = "AUTHOR_ID") },
			inverseJoinColumns = { @JoinColumn(name = "BOOK_ID") })
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
		return "author with Id: " + this.authorId + "; firstName: " + this.firstName
				+ "; lastName: " + this.secondName;
	}

}
