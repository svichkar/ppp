package com.nixsolutions.entity;

public class AuthorBook {
	private int authorId;
	private int bookId;

	public AuthorBook(int authorId, int bookId) {
		super();
		this.authorId = authorId;
		this.bookId = bookId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

}
