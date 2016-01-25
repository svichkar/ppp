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
	private Long authorId;
	@Id
	@Column(name = "BOOK_ID", nullable=false)
	private Long bookId;

	public AuthorBook(Long authorId, Long bookId) {
		super();
		this.authorId = authorId;
		this.bookId = bookId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
	public String toString() {
		return "book Id: " + this.bookId + "; authorId: " + this.authorId;

	}
}
