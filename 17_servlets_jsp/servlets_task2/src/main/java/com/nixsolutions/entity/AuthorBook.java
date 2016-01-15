package com.nixsolutions.entity;

public class AuthorBook {
	private Long authorId;
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
