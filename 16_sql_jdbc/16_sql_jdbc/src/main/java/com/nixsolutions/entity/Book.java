package com.nixsolutions.entity;

public class Book {
	private Integer bookId;
	private String name;
	private Integer cellId;
	private Integer categoryId;

	public Integer getBookId() {
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
