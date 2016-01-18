package com.nixsolutions.entity;

public class Book {
	private Long bookId;
	private String name;
	private Long cellId;
	private Long categoryId;

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

	public Long getCellId() {
		return cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public String toString() {
		return "book with bookId: " + this.bookId + "; name: " + this.name;
	}

}
