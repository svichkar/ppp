package com.nixsolutions.entity;

public class Cell {
	private Long cellId;
	private String name;

	public Cell(String name) {
		super();
		this.name = name;
	}

	public Long getCellId() {
		return cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Cell with cellId: " + this.cellId + "; name: " + this.name;

	}
}
