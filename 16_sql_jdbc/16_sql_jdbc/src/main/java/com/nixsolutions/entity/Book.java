package com.nixsolutions.entity;

public class Book {
	private int bookId;
	private String name;
	private int cellId;
	private int categoryId;
	private int authorId;

	public Book(int bookId, String name, int cellId, int categoryId,
			int authorId) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.cellId = cellId;
		this.categoryId = categoryId;
		this.authorId = authorId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

}
