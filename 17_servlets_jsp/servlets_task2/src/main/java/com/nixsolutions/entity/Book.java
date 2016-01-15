package com.nixsolutions.entity;

public class Book {
	private Long bookId;
	private String name;
	private Integer cellId;
	private Integer categoryId;
	//private Integer authorId;

	/*
	public Book(int bookId, String name, int cellId, int categoryId,
			int authorId) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.cellId = cellId;
		this.categoryId = categoryId;
		this.authorId = authorId;
	}
*/
	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String toString() {
		return "book with bookId: " + this.bookId + "; name: " + this.name;
	}

}
