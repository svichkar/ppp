package com.nixsolutions.entity;

public class Category {
	private Long categoryId;
	private String name;

	public Category(String name) {
		super();
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "category with Id: " + this.categoryId + "; name: " + this.name;

	}
}
