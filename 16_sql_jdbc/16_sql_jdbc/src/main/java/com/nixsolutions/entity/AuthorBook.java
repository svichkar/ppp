package com.nixsolutions.entity;

public class AuthorBook {
	private Integer authorId;
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

}
