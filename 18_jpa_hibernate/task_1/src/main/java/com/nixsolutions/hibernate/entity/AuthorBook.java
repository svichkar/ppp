package com.nixsolutions.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author_book")
public class AuthorBook implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "AUTHOR_ID", nullable=false)
	private Integer authorId;
	@Id
	@Column(name = "BOOK_ID", nullable=false)
	private Integer bookId;

	public AuthorBook(int authorId, int bookId) {
		super();
		this.authorId = authorId;
		this.bookId = bookId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public String toString() {
		return "book Id: " + this.bookId + "; authorId: " + this.authorId;

	}
}
